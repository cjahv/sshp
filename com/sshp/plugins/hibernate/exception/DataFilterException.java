package com.sshp.plugins.hibernate.exception;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年10月12日 16:01
 */
public class DataFilterException extends RuntimeException {
  public DataFilterException() {
  }

  public DataFilterException(String message) {
    super(message);
  }

  public DataFilterException(String message, Throwable cause) {
    super(message, cause);
  }

  public DataFilterException(Throwable cause) {
    super(cause);
  }

  public DataFilterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
