package com.sshp.plugins.hibernate.core.filter;

import com.sshp.plugins.hibernate.core.impl.OrderCondition;

/**
 * 排序
 * Created by jahv on 2016/11/1.
 */
public class Order extends org.hibernate.criterion.Order implements OrderCondition {
  /**
   * Constructor for Order.  Order instances are generally created by factory methods.
   *
   * @param propertyName
   * @param ascending
   * @see #asc
   * @see #desc
   */
  protected Order(String propertyName, boolean ascending) {
    super(propertyName, ascending);
  }

  /**
   * Ascending order
   *
   * @param propertyName The property to order on
   * @return The build Order instance
   */
  public static Order asc(String propertyName) {
    return new Order(propertyName, true);
  }

  /**
   * Descending order.
   *
   * @param propertyName The property to order on
   * @return The build Order instance
   */
  public static org.hibernate.criterion.Order desc(String propertyName) {
    return new Order(propertyName, false);
  }

}
