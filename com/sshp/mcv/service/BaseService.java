package com.sshp.mcv.service;

import com.sshp.config.SpringBean;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.mcv.dao.BaseDao;
import com.sshp.mcv.manage.BaseServiceAndDaoImpl;
import com.sshp.mcv.manage.MvcManage;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.impl.Condition;
import com.sshp.plugins.hibernate.select.DataFilter;
import com.sshp.plugins.hibernate.select.DataResult;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;

/**
 * 内容摘要 ：基础业务层
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Transactional
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
  public long count() {
    return dao.count();
  }

  @Override
  public long count(Filter... filters) {
    return dao.count(filters);
  }

  @Override
  public T get(Long id) {
    return dao.get(id);
  }

  @Override
  public DataResult<T> get(String key) {
    return dao.get(key);
  }

  @Override
  public DataResult<T> get(Filter... filters) {
    return dao.get(filters);
  }

  @Override
  public DataResult<T> get(String key, Filter... filters) {
    return dao.get(key, filters);
  }

  @Override
  public DataResult<T> get(com.sshp.plugins.hibernate.core.filter.Order... orders) {
    return dao.get(orders);
  }

  @Override
  public DataResult<T> get(String key, com.sshp.plugins.hibernate.core.filter.Order... orders) {
    return dao.get(key, orders);
  }

  @Override
  public DataResult<T> get(String key, Condition... objects) {
    return dao.get(key, objects);
  }

  @Override
  public DataResult<T> get(Condition... objects) {
    return dao.get(objects);
  }

  @Override
  public DataResult<T> get(DataFilter<T> filter) {
    return dao.get(filter);
  }

  @Override
  public int save(T entity) {
    return dao.save(entity);
  }

  @Override
  public int save(String keys, T entity) {
    return dao.save(keys, entity);
  }

  @Override
  public int delete(long... ids) {
    return dao.delete(ids);
  }

  @Override
  public int delete(Filter... filters) {
    return dao.delete(filters);
  }

  @Override
  public int logicDelete(long... ids) {
    return dao.logicDelete(ids);
  }

  @Override
  public int logicDelete(Filter... filters) {
    return dao.logicDelete(filters);
  }

  @Override
  public int heal(long... ids) {
    return dao.heal(ids);
  }

  @Override
  public int heal(Filter... filters) {
    return dao.heal(filters);
  }

  @Override
  public Query getNamedQuery(String name) {
    return dao.getNamedQuery(name);
  }
}
