package com.sshp.plugins.hibernate.core.criterion;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;

/**
 * 内容摘要 ：使用sql排序
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月01日 14:30
 */
public class SqlOrder extends Order {
  private boolean ascending;
  private String propertyName;

  /**
   * Ascending order
   *
   * @param propertyName The property to order on
   * @return The build Order instance
   */
  public static SqlOrder asc(String propertyName) {
    return new SqlOrder(propertyName, true);
  }

  /**
   * Descending order.
   *
   * @param propertyName The property to order on
   * @return The build Order instance
   */
  public static SqlOrder desc(String propertyName) {
    return new SqlOrder(propertyName, false);
  }

  /**
   * Constructor for Order.  Order instances are generally created by factory methods.
   *
   * @see #asc
   * @see #desc
   */
  protected SqlOrder(String propertyName, boolean ascending) {
    super(propertyName, ascending);
    this.propertyName = propertyName;
    this.ascending = ascending;
  }

  /**
   * Render the SQL fragment
   *
   * @param criteria      The criteria
   * @param criteriaQuery The overall query
   * @return The ORDER BY fragment for this ordering
   */
  public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
    final String[] columns = StringUtils.split(propertyName, ',');

    final StringBuilder fragment = new StringBuilder();
    for (int i = 0; i < columns.length; i++) {
      fragment.append(columns[i]).append(" ").append(ascending ? "asc" : "desc");
      if (i < columns.length - 1) {
        fragment.append(", ");
      }
    }

    return fragment.toString();
  }
}
