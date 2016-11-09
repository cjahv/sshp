package com.sshp.plugins.hibernate.core;

import java.util.Map;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年10月14日 11:21
 */
public class WhereResult {
  private String whereStr;
  private Map<String, Object> param;

  WhereResult(String string, Map<String, Object> param) {
    this.whereStr = string;
    this.param = param;
  }

  public String getWhereStr() {
    return whereStr;
  }

  public Map<String, Object> getParam() {
    return param;
  }

  public void setParam(Map<String, Object> param) {
    this.param = param;
  }
}
