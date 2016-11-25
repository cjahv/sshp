package com.sshp.plugins.hibernate.allsql;

import com.sshp.core.model.entity.BaseEntityImpl;

import java.util.List;

/**
 * 注入结果
 * Created by jahv on 2016/11/23.
 */
public class JoinResult<T extends BaseEntityImpl> {
  private Object result;

  public Object result() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public List<T> list(){
    return (List<T>) result;
  }
}
