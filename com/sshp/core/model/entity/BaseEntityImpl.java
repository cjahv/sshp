package com.sshp.core.model.entity;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public interface BaseEntityImpl<T> {
  T getId();

  void setId(T id);
}
