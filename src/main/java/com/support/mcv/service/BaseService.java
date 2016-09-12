package com.support.mcv.service;

import com.support.config.SpringBean;
import com.support.core.model.entity.BaseEntityImpl;
import com.support.mcv.dao.BaseDao;
import com.support.mcv.manage.BaseServiceAndDaoImpl;
import com.support.mcv.manage.MvcManage;
import com.support.plugins.hibernate.select.DataFilter;
import com.support.plugins.hibernate.select.Filter;
import org.hibernate.criterion.Order;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public class BaseService<T extends BaseEntityImpl> extends MvcManage<T> implements BaseServiceAndDaoImpl<T> {
    private BaseDao<T> dao;

    public BaseService() {
        super();
        dao = SpringBean.getDao(entityClass);
    }

    public BaseService(Class<T> defaultClass) {
        super(defaultClass);
        dao = SpringBean.getDao(entityClass);
    }

    @Override
    public T get(Long id) {
        return dao.get(id);
    }

    @Override
    public DataFilter<T> get() {
        return dao.get();
    }

    @Override
    public DataFilter<T> get(String key) {
        return dao.get(key);
    }

    @Override
    public DataFilter<T> get(Filter... filters) {
        return dao.get(filters);
    }

    @Override
    public DataFilter<T> get(String key, Filter... filters) {
        return dao.get(key, filters);
    }

    @Override
    public DataFilter<T> get(Order... orders) {
        return dao.get(orders);
    }

    @Override
    public DataFilter<T> get(String key, Order... orders) {
        return dao.get(key, orders);
    }
}
