package com.sshp.plugins.hibernate.select;

import com.sshp.plugins.hibernate.core.criterion.SOrder;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.utils.Reflex;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;

import java.util.List;
import java.util.Set;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/18
 */
public class ResolveFilter extends GenerateFilter {
  List<Projection> projectionList;
  boolean isGroup;

  ResolveFilter(Criteria criteria) {
    this.criteria = criteria;
  }

  ResolveFilter(DetachedCriteria criteria) {
    this.detachedCriteria = criteria;
  }

  void buildKey(String[] keys) {
    ProjectionList pList = Projections.projectionList();
    if (keys != null && keys.length > 0) {
      for (String key : keys) {
        if (StringUtils.isEmpty(key)) continue;
        Projection projection;
        String model = null;
        if (key.lastIndexOf(':') > 0) {
          String[] keyArray = StringUtils.split(key, ':');
          model = keyArray[1];
          key = keyArray[0];
        }
        key = generateAlias(key);
        if (model != null) {
          if ("group".equals(model)) {
            projection = Projections.groupProperty(key);
          } else {
            projection = (Projection) Reflex.invokeStaticMethod(Projections.class, model, key);
          }
        } else {
          projection = Projections.property(key);
        }
        if (projection.isGrouped()) this.isGroup = true;
        pList.add(projection);
      }
    }
    if (this.projectionList != null) {
      for (Projection projection : this.projectionList) {
        if (projection.isGrouped()) this.isGroup = true;
        pList.add(projection);
      }
    }
    if (pList.getLength() > 0) criteria.setProjection(pList);
  }

  void buildFilter(Set<Filter> filters) {
    if (filters != null) {
      for (Filter filter : filters) {
        Criterion criterion = super.buildFilter(filter);
        if (criterion == null) continue;
        criteria.add(criterion);
      }
    }
  }

  void buildOrder(Order[] orders) {
    if (orders != null) {
      for (Order order : orders) {
        if (order instanceof SOrder) {
          ((SOrder) order).setAliasMap(aliasMap);
          criteria.addOrder(order);
          continue;
        }
        String name = order.getPropertyName();
        int split = name.lastIndexOf('.');
        if (split > 0) {
          String alias = super.generateAlias(name);
          if(!alias.equals(name)){
            Reflex.set(order, "propertyName", alias);
          }
        }
        criteria.addOrder(order);
      }
    }
  }

  List list() {
    return criteria.list();
  }

  void buildPage(int start, int length) {
    if (start > 0) criteria.setFirstResult(start);
    if (length > 0) criteria.setMaxResults(length);
  }
}
