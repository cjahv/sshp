package com.support.core.exception;

/**
 * 内容摘要 ：内部错误
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月12日 13:01
 */
public class InsideException extends RuntimeException {
    public InsideException(String message) {
        super(message);
    }

    public InsideException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsideException(Throwable cause) {
        super(cause);
    }

    public InsideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InsideException() {
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
