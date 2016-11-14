package com.sshp.plugins.hibernate.delete;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.WhereCore;
import com.sshp.plugins.hibernate.core.WhereResult;
import com.sshp.plugins.hibernate.core.filter.Filter;

/**
 * 内容摘要 ：删除逻辑类
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年10月14日 10:39
 */
public class Delete<T extends BaseEntityImpl> extends WhereCore<T> {
  public int exec(Class<T> entityClass, Filter... filters) {
    WhereResult result = buildFilter2Hql(filters);
    return getSession().createQuery("delete " + entityClass.getName() + " where " + result.getWhereStr()).setProperties(result.getParam()).executeUpdate();
  }
}
