package com.sshp.plugins.hibernate.select;

import com.linkcubic.model.entity.product.Product;
import com.sshp.core.exception.InsideException;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.mcv.entity.ReEntityImpl;
import com.sshp.plugins.hibernate.core.KeyCore;
import com.sshp.plugins.hibernate.core.criterion.SQLProjection;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.filter.Order;
import com.sshp.plugins.hibernate.core.filter.Other;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.sql.JoinType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 内容摘要 ：查询条件超集
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/18
 */
@SuppressWarnings("WeakerAccess")
public class DataFilter<T extends BaseEntityImpl> extends KeyCore<T> {

  private int start = 0;
  private int length = 400;

  private List list;
  ResolveFilter resolveFilter;

  Class<T> entityClass;

  public DataFilter() {
    super();
    Class thisClass = getClass();
    Type genericSuperclass = thisClass.getGenericSuperclass();
    if (genericSuperclass != null && genericSuperclass instanceof ParameterizedType) {
      ParameterizedType type = (ParameterizedType) genericSuperclass;
      Type[] types = type.getActualTypeArguments();
      if (types.length > 0) {
        //noinspection unchecked
        entityClass = (Class<T>) types[0];
      }
    }
    setCriteria();
  }

  public DataFilter(Class<T> tClass) {
    super();
    this.entityClass = tClass;
    setCriteria();
  }

  private void setCriteria() {
    Criteria criteria;
    if (getSession() != null) {
      criteria = getSession().createCriteria(entityClass);
      resolveFilter = new ResolveFilter(criteria);
    } else {
      resolveFilter = new ResolveFilter(DetachedCriteria.forClass(entityClass));
    }
  }

  private String[] keys;

  public DataFilter<T> key(String... key) {
    if (key.length == 1) {
      key = compileKeys(key[0], entityClass);
    }
    this.keys = ArrayUtils.addAll(this.keys, key);
    return this;
  }

  private Set<Filter> filters = new HashSet<>();
  private boolean existDeleteStatus;

  public DataFilter<T> filter(Filter... filters) {
    for (Filter filter : filters) {
      if (filter == null) continue;
      if (!existDeleteStatus && filter.getName().length() == 12 && filter.getName().charAt(0) == 'd' && filter.getName().charAt(6) == 'S' && filter.getName().equals("deleteStatus")) {
        existDeleteStatus = true;
      }
      this.filters.add(filter);
    }
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
    if (resolveFilter.criteria == null && getSession() == null) {
      throw new HibernateException("不能获取到 session");
    } else if (resolveFilter.criteria == null) {
      resolveFilter.criteria = resolveFilter.detachedCriteria.getExecutableCriteria(getSession());
    }
    resolveFilter.buildKey(this.keys);
    if (entityClass.isAssignableFrom(ReEntityImpl.class) && !existDeleteStatusFilter()) {
      this.filter(Filter.eq("deleteStatus", false));
    }
    resolveFilter.buildFilter(this.filters);
    resolveFilter.buildOrder(this.orders);
    for (Other other : this.others) {
      switch (other.getIndex()) {
        case 0:
          this.one();
          break;
        case 1:
          this.all();
          break;
        case 2:
          this.length((Integer) other.getArgs(1));
          this.start((Integer) other.getArgs(0));
      }
    }
    resolveFilter.buildPage(this.start, this.length);
    list = resolveFilter.list();
  }

  private boolean existDeleteStatusFilter() {
    return existDeleteStatus;
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

  private Set<Other> others = new HashSet<>();

  public DataFilter<T> other(Other other) {
    this.others.add(other);
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

  public DataFilter<T> cache(CacheMode cacheMode, String cacheName) {
    return null;
  }

  public String[] getKeys() {
    return keys;
  }

  public Filter[] getFilter() {
    return this.filters.toArray(new Filter[this.filters.size()]);
  }

  public static <T extends BaseEntityImpl> DataFilter<T> load(Class<T> tClass) {
    return new DataFilter<T>(tClass);
  }
}
