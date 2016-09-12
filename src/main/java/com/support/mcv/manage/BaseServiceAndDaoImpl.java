package com.support.mcv.manage;

import com.support.core.model.entity.BaseEntityImpl;
import com.support.plugins.hibernate.select.DataFilter;
import com.support.plugins.hibernate.select.Filter;
import org.hibernate.criterion.Order;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/29
 */
public interface BaseServiceAndDaoImpl<T extends BaseEntityImpl> {
    T get(Long id);

    DataFilter<T> get();

    DataFilter<T> get(String key);

    DataFilter<T> get(Filter... filters);

    DataFilter<T> get(String key, Filter... filters);

    DataFilter<T> get(Order... orders);

    DataFilter<T> get(String key, Order... orders);
}
