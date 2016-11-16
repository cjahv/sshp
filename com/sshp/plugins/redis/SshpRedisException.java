package com.sshp.plugins.redis;

/**
 * 插件redis异常
 * Created by jahv on 2016/11/15.
 */
public class SshpRedisException extends RuntimeException{
  public SshpRedisException() {
  }

  public SshpRedisException(String message) {
    super(message);
  }

  public SshpRedisException(String message, Throwable cause) {
    super(message, cause);
  }

  public SshpRedisException(Throwable cause) {
    super(cause);
  }

  public SshpRedisException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
