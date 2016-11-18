package com.sshp.plugins.hibernate.core.criterion;

import com.sshp.plugins.hibernate.core.filter.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/9/9
 */
public abstract class SOrder extends Order {
  protected Map<String, Integer> aliasMap = new HashMap<>();

  /**
   * Constructor for Order.  Order instances are generally created by factory methods.
   *
   * @param propertyName
   * @param ascending
   * @see #asc
   * @see #desc
   */
  protected SOrder(String propertyName, boolean ascending) {
    super(propertyName, ascending);
  }

  public Map<String, Integer> getAliasMap() {
    return aliasMap;
  }

  public void setAliasMap(Map<String, Integer> aliasMap) {
    this.aliasMap = aliasMap;
  }
}
