package com.sshp.core.model.dto.result;

import com.sshp.core.model.enums.ExceptionEnum;

import java.util.Date;
import java.util.Map;

/**
 * 内容摘要 ：异常JSON结果
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年01月26日
 */
public class ExceptionResult extends JsonResult {
  private boolean exception;
  private String exceptionMsg;
  private String exceptionClass;
  private ExceptionEnum exceptionType;
  private String location;
  private Date timestamp;
  private String trace;

  public ExceptionResult() {
    this.exception = true;
    exceptionType = ExceptionEnum.alert;
    super.setMsg(":( 服务器发生了未知错误!");
    super.setSuccess(false);
  }

  public ExceptionResult(Map<String, Object> errorAttributes) {
    this();
    String msg = (String) errorAttributes.get("msg");
    if (msg != null) {
      super.setMsg(msg);
    } else {
      this.exceptionMsg = (String) errorAttributes.get("message");
      this.exceptionClass = (String) errorAttributes.get("exception");
      this.location = (String) errorAttributes.get("path");
    }
    this.timestamp = (Date) errorAttributes.get("timestamp");
    this.trace = (String) errorAttributes.get("trace");
    ExceptionEnum type = (ExceptionEnum) errorAttributes.get("type");
    if (type != null) this.exceptionType = type;
  }

  public boolean isException() {
    return exception;
  }

  public void setException(boolean exception) {
    this.exception = exception;
  }

  public String getExceptionMsg() {
    return exceptionMsg;
  }

  public void setExceptionMsg(String exceptionMsg) {
    this.exceptionMsg = exceptionMsg;
  }

  public String getExceptionClass() {
    return exceptionClass;
  }

  public void setExceptionClass(String exceptionClass) {
    this.exceptionClass = exceptionClass;
  }

  public ExceptionEnum getExceptionType() {
    return exceptionType;
  }

  public void setExceptionType(ExceptionEnum exceptionType) {
    this.exceptionType = exceptionType;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getTrace() {
    return trace;
  }

  public void setTrace(String trace) {
    this.trace = trace;
  }
}
