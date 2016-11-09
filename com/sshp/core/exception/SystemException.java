package com.sshp.core.exception;


import com.sshp.core.model.enums.ExceptionEnum;

/**
 * 内容摘要 ：系统级异常
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月12日 12:58
 */
public class SystemException extends RuntimeException {
  private ExceptionEnum type;

  public SystemException(String message) {
    super(message);
    this.type = ExceptionEnum.alert;
  }

  public SystemException(String message, Throwable cause) {
    super(message, cause);
  }

  public SystemException(Throwable cause) {
    super(cause);
  }

  public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public SystemException(String message, Throwable cause, ExceptionEnum type) {
    super(message, cause);
    this.type = type;
  }

  public SystemException(String message, ExceptionEnum type) {
    super(message);
    this.type = type;
  }

  public SystemException() {
  }

  @Override
  public String getMessage() {
    return super.getMessage();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  public ExceptionEnum getType() {
    return type;
  }

  public SystemException setType(ExceptionEnum type) {
    this.type = type;
    return this;
  }
}
