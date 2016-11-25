package com.sshp.core.model.dto.page;

import com.sshp.core.model.dto.result.PageResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.filter.Order;
import com.sshp.plugins.hibernate.core.filter.Other;
import com.sshp.plugins.hibernate.select.DataFilter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基础分页 dto
 * Created by jahv on 2016/11/9.
 */
public class BasisPage implements Page {
  private String keys;
  private Set<Filter> filters;
  private Set<Order> orders;
  private Set<Other> others;

  /**
   * 对筛选器设置key
   *
   * @param key 字段列表，遵循key语法
   *            <ul>
   *            <li>* 全部字段</li>
   *            <li>{xxx} 排除xxx字段，必须紧跟在*前面</li>
   *            <li>alis[key1,key2, ...] alis下面的key1,key2, ... 字段</li>
   *            </ul>
   * @return 原对象
   */
  public BasisPage key(String key) {
    if (this.keys == null) this.keys = key;
    else this.keys = this.keys + "," + key;
    return this;
  }

  /**
   * 对筛选器设置filter
   *
   * @param filters 条件序列
   * @return 原对象
   */
  public BasisPage add(Filter... filters) {
    if(this.filters==null) this.filters = new HashSet<>();
    Collections.addAll(this.filters, filters);
    return this;
  }

  /**
   * 其他条件设置
   *
   * @param conditions 条件序列
   * @return 原对象
   */
  public BasisPage other(Other... conditions) {
    if(this.others==null) this.others = new HashSet<>();
    Collections.addAll(this.others, conditions);
    return this;
  }

  /**
   * 对筛选器设置order
   *
   * @param orders 排序序列
   * @return 原对象
   */
  public BasisPage order(Order... orders) {
    if(this.orders==null) this.orders = new HashSet<>();
    Collections.addAll(this.orders, orders);
    return this;
  }

  @Override
  public <T extends BaseEntityImpl> DataFilter<T> filter(Class<T> entityClass) {
    DataFilter<T> dataFilter = new DataFilter<>(entityClass);
    if(this.keys!=null) dataFilter.key(this.keys);
    if(this.filters!=null)dataFilter.filter(this.filters.toArray(new Filter[this.filters.size()]));
    if(this.orders!=null)dataFilter.order(this.orders.toArray(new Order[this.orders.size()]));
    if(this.others!=null) this.others.forEach(dataFilter::other);
    return dataFilter;
  }

  @Override
  public boolean isAutoCount() {
    return true;
  }

  @Override
  public boolean isAutoCountAll() {
    return false;
  }

  @Override
  public <T extends BaseEntityImpl> PageResult<T> getResult(List<T> list, Integer count, Integer countTail) {
    return new PageResult<>(list, count, countTail);
  }
}
