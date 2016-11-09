package com.sshp.plugins.hibernate.core.criterion;

import com.sshp.core.exception.InsideException;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.internal.CriteriaImpl;

import java.util.Map;

/**
 * 内容摘要 ：Formula快速排序
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月18日 10:56
 */
public class OrderFormula extends Order {
  private boolean ascending;
  private String propertyName;
  private Map<String, String> aliasMap;

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
    String alias = aliasMap.get(propertyName);
    if (alias == null) throw new InsideException("无法获取到" + propertyName + "的别名");
    return ((CriteriaImpl) criteria).getProjection().getColumnAliases(alias, 0)[0]
            + " " + (ascending ? "asc" : "desc");
  }

  public void setAliasMap(Map<String, String> aliasMap) {
    this.aliasMap = aliasMap;
  }
}
