package com.support.plugins.hibernate.core;

import com.support.config.SpringBean;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/14
 */
public abstract class SessionCore {

    org.hibernate.SessionFactory sessionFactory;

    protected SessionCore() {
        sessionFactory = SpringBean.getBean(org.hibernate.SessionFactory.class);
    }

    protected org.hibernate.Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
