package com.sshp.plugins.hibernate.select;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.KeyCore;
import com.sshp.plugins.hibernate.core.criterion.SQLProjection;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.filter.Other;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.sql.JoinType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 内容摘要 ：查询条件超集
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/18
 */
@SuppressWarnings("WeakerAccess")
public class DataFilter<T extends BaseEntityImpl> extends KeyCore {
  private int start = 0;
  private int length = 400;

  private List list;
  ResolveFilter resolveFilter;

  Class<T> entityClass;

  public DataFilter(Class<T> tClass) {
    super();
    this.entityClass = tClass;
    Criteria criteria = getSession().createCriteria(entityClass);
    resolveFilter = new ResolveFilter(criteria);
  }

  private String[] keys;

  public DataFilter<T> key(String... key) {
    if (key.length == 1) {
      key = compileKeys(key[0], entityClass);
    }
    this.keys = ArrayUtils.addAll(this.keys, key);
    return this;
  }

  private Filter[] filters;

  public DataFilter<T> filter(Filter... filters) {
    this.filters = ArrayUtils.addAll(this.filters, filters);
    return this;
  }

  private Order[] orders;

  public DataFilter<T> order(Order... orders) {
    this.orders = ArrayUtils.addAll(this.orders, orders);
    return this;
  }

  public List result() {
    if (list == null) build();
    return list;
  }

  private void build() {
    resolveFilter.buildKey(this.keys);
    resolveFilter.buildFilter(this.filters);
    resolveFilter.buildOrder(this.orders);
    resolveFilter.buildPage(this.start, this.length);
    list = resolveFilter.list();
  }

  public DataFilter<T> projection(Projection... projections) {
    if (resolveFilter.projectionList == null) resolveFilter.projectionList = new ArrayList<>();
    List<String> tmpKey = new ArrayList<>();
    for (Projection projection : projections) {
      if (projection instanceof SQLProjection) {
        ((SQLProjection) projection).setResolveFilter(resolveFilter);
      }
      Collections.addAll(tmpKey, projection.getAliases());
      resolveFilter.projectionList.add(projection);
    }
    if (this.keys == null) this.keys = tmpKey.toArray(new String[tmpKey.size()]);
    else this.keys = ArrayUtils.addAll(this.keys, tmpKey.toArray(new String[tmpKey.size()]));
    return this;
  }

  public DataFilter<T> joinType(String name, JoinType joinType) {
    if (resolveFilter.joinType == null) resolveFilter.joinType = new HashMap<>();
    resolveFilter.joinType.put(name, joinType);
    return this;
  }

  public boolean isGroup() {
    return resolveFilter.isGroup;
  }

  public DataFilter<T> other(Other other) {
    switch (other.getIndex()) {
      case 0:return this.one();
      case 1:return this.all();
      case 2:
        this.length((Integer) other.getArgs(1));
        this.start((Integer) other.getArgs(0));
    }
    return this;
  }

  public DataFilter<T> one() {
    this.length = 1;
    return this;
  }

  public DataFilter<T> length(int length) {
    this.length = length;
    return this;
  }

  public DataFilter<T> all() {
    this.length = 0;
    return this;
  }

  public DataFilter<T> start(int start) {
    this.start = start;
    return this;
  }

  public String[] getKeys() {
    return keys;
  }
}
