package com.support.mcv.dao;

import com.support.core.model.entity.BaseEntityImpl;
import com.support.mcv.manage.BaseServiceAndDaoImpl;
import com.support.mcv.manage.MvcManage;
import com.support.plugins.hibernate.select.DataFilter;
import com.support.plugins.hibernate.select.Filter;
import org.hibernate.criterion.Order;

import javax.transaction.Transactional;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Transactional
public class BaseDao<T extends BaseEntityImpl> extends MvcManage<T> implements BaseServiceAndDaoImpl<T> {
    public BaseDao() {
    }

    public BaseDao(Class<T> defaultClass) {
        super(defaultClass);
    }

    @Override
    public T get(Long id) {
        return get().filter(Filter.eq("id", id)).entity();
    }

    @Override
    public DataFilter<T> get() {
        return new DataFilter<>(entityClass);
    }

    @Override
    public DataFilter<T> get(String key) {
        return get().key(key);
    }

    @Override
    public DataFilter<T> get(Filter... filters) {
        return get().filter(filters);
    }

    @Override
    public DataFilter<T> get(String key, Filter... filters) {
        return get().key(key).filter(filters);
    }

    @Override
    public DataFilter<T> get(Order... orders) {
        return get().order(orders);
    }

    @Override
    public DataFilter<T> get(String key, Order... orders) {
        return get().key(key).order(orders);
    }
}
