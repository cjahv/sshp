package com.sshp.plugins.hibernate.core;

import com.sshp.config.SpringBean;

/**
 * 内容摘要 ：session获取，依赖SpringBean
 * @see SpringBean
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/14
 */
public abstract class SessionCore {

  org.hibernate.SessionFactory sessionFactory;

  public SessionCore() {
    sessionFactory = SpringBean.getBean(org.hibernate.SessionFactory.class);
  }

  protected org.hibernate.Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
