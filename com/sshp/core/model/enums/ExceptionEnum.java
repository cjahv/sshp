package com.sshp.core.model.enums;

/**
 * 内容摘要 ：异常类型枚举
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月20日 18:23
 */
public enum ExceptionEnum {
  /**
   * 默认,提示错误信息
   */
  alert,
  /**
   * 不重要错误,忽略
   */
  ignore,
  /**
   * 登录错误
   */
  login,
  /**
   * 将错误以消息方式提示
   */
  message,
  /**
   * 不展示详细消息
   */
  info,

  //删除错误

  /**
   * 数据有外键关联
   */
  dataLinked
}
