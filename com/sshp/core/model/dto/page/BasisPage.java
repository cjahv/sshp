package com.sshp.core.model.dto.page;

import com.sshp.core.model.dto.result.PageResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.filter.Order;
import com.sshp.plugins.hibernate.core.filter.Other;
import com.sshp.plugins.hibernate.select.DataFilter;

/**
 * 基础分页 dto
 * Created by jahv on 2016/11/9.
 */
public class BasisPage<T extends BaseEntityImpl> implements Page<T> {
  private DataFilter<T> dataFilter;

  public BasisPage(Class<T> entityClass) {
    this.dataFilter = new DataFilter<>(entityClass);
  }

  /**
   * 对筛选器设置key
   *
   * @param key 字段列表，遵循key语法
   *            <ul>
   *              <li>* 全部字段</li>
   *              <li>{xxx} 排除xxx字段，必须紧跟在*前面</li>
   *              <li>alis[key1,key2, ...] alis下面的key1,key2, ... 字段</li>
   *            </ul>
   * @return 原对象
   */
  Page<T> key(String key) {
    dataFilter.key(key);
    return this;
  }

  /**
   * 对筛选器设置filter
   *
   * @param filters 条件序列
   * @return 原对象
   */
  Page<T> filter(Filter... filters) {
    dataFilter.filter(filters);
    return this;
  }

  /**
   * 其他条件设置
   *
   * @param conditions 条件序列
   * @return 原对象
   */
  Page<T> other(Other... conditions) {
    for (Other other : conditions) {
      dataFilter.other(other);
    }
    return this;
  }

  /**
   * 对筛选器设置order
   *
   * @param orders 排序序列
   * @return 原对象
   */
  Page<T> order(Order... orders) {
    dataFilter.order(orders);
    return this;
  }

  @Override
  public DataFilter<T> filter() {
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
  public PageResult getResult(Object data, Integer count, Integer countTail) {
    return new PageResult(data, count, countTail);
  }
}
