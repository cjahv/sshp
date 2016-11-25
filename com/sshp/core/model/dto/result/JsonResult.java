package com.sshp.core.model.dto.result;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sshp.core.model.entity.BaseEntityImpl;

import java.io.Serializable;
import java.util.List;

public class JsonResult<T extends BaseEntityImpl> implements Serializable {
  private boolean success;            //结果状态
  private String msg;                    //信息
  private Object data;                //结果对象
  private List<T> list;
  private int hash;

  public JsonResult() {
    this.success = true;
  }

  public JsonResult(boolean success, String msg) {
    this.success = success;
    this.msg = msg;
  }

  public boolean isSuccess() {
    return success;
  }

  public JsonResult setSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public String getMsg() {
    return msg;
  }

  public JsonResult setMsg(String msg) {
    this.msg = msg;
    return this;
  }

  public Object getData() {
    if(data==null) return list;
    return data;
  }

  public JsonResult setData(Object data) {
    this.data = data;
    return this;
  }

  @JsonIgnore
  public List<T> list(){
    if(data==null) return list;
    //noinspection unchecked
    return (List<T>)data;
  }

  public void setList(List<T> list){
    this.list = list;
  }

  @Override
  public String toString() {
    return JSONObject.toJSONString(this);
  }

  public Integer getCode() {
    if (this.msg == null || this.msg.length() == 0) return null;
    if (hash != 0) return hash;
    char[] values = this.msg.toCharArray();
    if (values.length > 0) {
      boolean skip = false;
      for (char value : values) {
        if (value == '[') skip = true;
        if (value == ']') skip = false;
        if (skip) continue;
        hash = 31 * hash + value;
      }
    } else {
      return null;
    }
    return hash;
  }
}
