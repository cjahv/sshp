package com.sshp.plugins.hibernate.core.filter;

/**
 * 内容摘要 ：条件枚举
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月12日 15:46
 */
public enum Term {
  /**
   * 等于
   */
  eq,
  /**
   * 不等于
   */
  notEQ,
  /**
   * 日期等于(按天)
   */
  eqDate,
  /**
   * 大于等于
   */
  ge,
  /**
   * 大于
   */
  gt,
  /**
   * 小于等于
   */
  le,
  /**
   * 小于
   */
  lt,
  /**
   * like查询
   */
  like,
  StartLike,
  EndLike,
  /**
   * 值在一个列表中
   */
  in,
  /**
   * 值不在一个列表中
   */
  notIN,
  /**
   * 值不为null
   */
  notNULL,
  /**
   * 值为null
   */
  NULL,
  /**
   * 值为空字符串
   */
  empty,
  /**
   * 值不为空字符串
   */
  notEmpty,
  /**
   * 值在一个范围
   */
  between,
  /**
   * 日期在一个范围内
   */
  betweenDate,
  /**
   * 或者
   */
  or
}
