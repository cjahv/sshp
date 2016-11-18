package com.sshp.plugins.hibernate.core.criterion;

import com.sshp.core.exception.InsideException;
import com.sshp.plugins.hibernate.core.filter.Order;
import com.sshp.plugins.hibernate.select.GenerateKey;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.PropertyProjection;
import org.hibernate.internal.CriteriaImpl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 内容摘要 ：Formula快速排序
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月18日 10:56
 */
public class OrderFormula extends SOrder {
  private boolean ascending;
  private String propertyName;

  public OrderFormula(String propertyName, boolean ascending) {
    super(propertyName, ascending);
    this.propertyName = propertyName;
    this.ascending = ascending;
  }

  /**
   * Ascending order
   *
   * @return The build Order instance
   */
  public static Order asc(String propertyName) {
    return new OrderFormula(propertyName, true);
  }

  /**
   * Descending order.
   *
   * @return The build Order instance
   */
  public static Order desc(String propertyName) {
    return new OrderFormula(propertyName, false);
  }

  public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
    String alias = null;
    String[] aliases = ((CriteriaImpl) criteria).getProjection().getColumnAliases(0);
    Projection projection = ((CriteriaImpl) criteria).getProjection();
    try {
      Field field = projection.getClass().getDeclaredField("elements");
      field.setAccessible(true);
      List<Projection> projectionList = (List<Projection>) field.get(projection);
      for (int i = 0; i < projectionList.size(); i++) {
        if (projectionList.get(i) instanceof PropertyProjection) {
          String pn = ((PropertyProjection) projectionList.get(i)).getPropertyName();
          if (propertyName.equals(pn)) {
            alias = aliases[i];
            break;
          }
        }
      }
    } catch (NoSuchFieldException e) {
      throw new HibernateException("get elements field fail!", e);
    } catch (IllegalAccessException e) {
      throw new HibernateException("get elements value fail!", e);
    }
    if (alias == null) throw new InsideException("无法获取到" + propertyName + "的别名");
    return alias + " " + (ascending ? "asc" : "desc");
  }

}
