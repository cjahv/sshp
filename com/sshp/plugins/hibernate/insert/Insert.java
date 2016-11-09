package com.sshp.plugins.hibernate.insert;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.SessionCore;

import java.util.List;

/**
 * 内容摘要 ：新增数据逻辑类
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public class Insert<T extends BaseEntityImpl> extends SessionCore {

  public int exec(T entity) {
    getSession().save(entity);
    return 1;
  }

  public int exec(List<T> list) {
    for (T entity : list) {
      getSession().save(entity);
    }
    return list.size();
  }

  public int exec(T[] array) {
    for (T entity : array) {
      getSession().save(entity);
    }
    return array.length;
  }
}
