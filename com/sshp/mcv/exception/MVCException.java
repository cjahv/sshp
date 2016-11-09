package com.sshp.mcv.exception;

/**
 * mvc运行时异常
 * Created by jahv on 2016/11/2.
 */
public class MVCException extends RuntimeException {
  public MVCException() {
  }

  public MVCException(String message) {
    super(message);
  }

  public MVCException(String message, Throwable cause) {
    super(message, cause);
  }

  public MVCException(Throwable cause) {
    super(cause);
  }

  public MVCException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
