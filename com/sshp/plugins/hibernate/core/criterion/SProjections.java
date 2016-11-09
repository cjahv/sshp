package com.sshp.plugins.hibernate.core.criterion;

import org.hibernate.criterion.Projection;
import org.hibernate.type.Type;

/**
 * 重构hibernate5的Projections
 * <p>
 * 实现别名所有表别名
 *
 * @author JAHV
 * @see org.hibernate.criterion.Projections
 */
public final class SProjections {

  /**
   * A SQL projection, a typed select clause fragment
   *
   * @param sql           The SQL fragment
   * @param columnAliases The column aliases
   * @param types         The resulting types
   * @return The SQL projection
   * @see SQLProjection
   */
  public static Projection sqlProjection(String sql, String[] columnAliases, Type[] types) {
    return new SQLProjection(sql, columnAliases, types);
  }

  /**
   * A grouping SQL projection, specifying both select clause and group by clause fragments
   *
   * @param sql           The SQL SELECT fragment
   * @param groupBy       The SQL GROUP BY fragment
   * @param columnAliases The column aliases
   * @param types         The resulting types
   * @return The SQL projection
   * @see SQLProjection
   */
  @SuppressWarnings("UnusedDeclaration")
  public static Projection sqlGroupProjection(String sql, String groupBy, String[] columnAliases, Type[] types) {
    return new SQLProjection(sql, groupBy, columnAliases, types);
  }

  private SProjections() {
    //cannot be instantiated
  }

}
