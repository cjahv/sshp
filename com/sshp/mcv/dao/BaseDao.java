package com.sshp.mcv.dao;

import com.sshp.config.SpringBean;
import com.sshp.core.model.dto.page.Page;
import com.sshp.core.model.dto.result.PageResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.mcv.entity.ReEntityImpl;
import com.sshp.mcv.exception.MVCException;
import com.sshp.mcv.manage.BaseServiceAndDaoImpl;
import com.sshp.mcv.manage.MvcManage;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.filter.Order;
import com.sshp.plugins.hibernate.core.filter.Other;
import com.sshp.plugins.hibernate.core.impl.Condition;
import com.sshp.plugins.hibernate.delete.Delete;
import com.sshp.plugins.hibernate.insert.Insert;
import com.sshp.plugins.hibernate.select.DataFilter;
import com.sshp.plugins.hibernate.select.DataResult;
import com.sshp.plugins.hibernate.update.Update;
import org.hibernate.Query;
import org.hibernate.SQLQuery;


/**
 * 内容摘要 ：基础数据层
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public class BaseDao<T extends BaseEntityImpl> extends MvcManage<T> implements BaseServiceAndDaoImpl<T> {
  public BaseDao() {
    sessionFactory = SpringBean.getBean(org.hibernate.SessionFactory.class);
  }

  public BaseDao(Class<T> defaultClass) {
    super(defaultClass);
    sessionFactory = SpringBean.getBean(org.hibernate.SessionFactory.class);
  }

  private org.hibernate.SessionFactory sessionFactory;

  private org.hibernate.Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public Integer count() {
    return get("id:count").getInteger();
  }

  @Override
  public Integer count(Filter... filters) {
    return get("id:count", filters).getInteger();
  }

  @Override
  public T get(Long id) {
    return new DataResult<>(get().filter(Filter.eq("id", id))).entity();
  }

  @Override
  public T get(String key, Long id) {
    return new DataResult<>(get().key(key).filter(Filter.eq("id", id))).entity();
  }

  private DataFilter<T> get() {
    return new DataFilter<>(entityClass);
  }

  @Override
  public DataResult<T> get(String key) {
    return new DataResult<>(get().key(key));
  }

  @Override
  public DataResult<T> get(Filter... filters) {
    return new DataResult<>(get().filter(filters));
  }

  @Override
  public DataResult<T> get(String key, Filter... filters) {
    return new DataResult<>(get().key(key).filter(filters));
  }

  @Override
  public DataResult<T> get(Order... orders) {
    return new DataResult<>(get().order(orders));
  }

  @Override
  public DataResult<T> get(String key, Order... orders) {
    return new DataResult<>(get().key(key).order(orders));
  }

  @Override
  public DataResult<T> get(String key, Condition... objects) {
    DataFilter<T> filter = get();
    if (key != null) filter.key(key);
    if (objects != null && objects.length > 0) {
      for (Condition object : objects) {
        if (object instanceof Filter) {
          filter.filter((Filter) object);
        } else if (object instanceof Order) {
          filter.order((Order) object);
        } else if (object instanceof Other) {
          filter.other((Other) object);
        }
      }
    }
    return new DataResult<>(filter);
  }

  @Override
  public DataResult<T> get(Condition... objects) {
    return get(null, objects);
  }

  @Override
  public DataResult<T> get(DataFilter<T> filter) {
    return new DataResult<>(filter);
  }

  @Override
  public PageResult get(Page<T> page) {
    Integer count = null, countAll = null;
    DataFilter<T> dataFilter = page.filter(entityClass);
    if (page.isAutoCount()) {
      count = count(dataFilter.getFilter());
    }
    if (page.isAutoCountAll()) {
      countAll = count();
    }
    return page.getResult(new DataResult<>(dataFilter).list(), count, countAll);
  }

  @Override
  public int save(T entity) {
    if (entity.getId() == null) {
      return new Insert<>().exec(entity);
    } else {
      return new Update<>().exec(entity);
    }
  }

  @Override
  public int save(String keys, T entity) {
    if (entity.getId() == null) {
      return new Insert<>().exec(entity);
    } else {
      return new Update<>().exec(keys, entity);
    }
  }

  @Override
  public int delete(long... ids) {
    if (ids.length == 1) {
      return delete(Filter.eq("id", ids[0]));
    } else if (ids.length > 1) {
      return delete(Filter.in("id", ids));
    } else {
      return delete(Filter.notNull("id"));
    }
  }

  @Override
  public int delete(Filter... filters) {
    if (entityClass.isAssignableFrom(ReEntityImpl.class)) {
      return logicDelete(filters);
    } else {
      return new Delete<T>().exec(entityClass, filters);
    }
  }

  @Override
  public int logicDelete(long... ids) {
    if (ids.length == 1) {
      return logicDelete(Filter.eq("id", ids[0]));
    } else if (ids.length > 1) {
      return logicDelete(Filter.in("id", ids));
    } else {
      return logicDelete(Filter.notNull("id"));
    }
  }

  @Override
  public int logicDelete(Filter... filters) {
    if (entityClass.isAssignableFrom(ReEntityImpl.class)) {
      BaseEntityImpl entity;
      try {
        entity = entityClass.newInstance();
        ReEntityImpl reEntity = (ReEntityImpl) entity;
        reEntity.setDeleteStatus(true);
      } catch (InstantiationException | IllegalAccessException e) {
        throw new MVCException(e);
      }
      return new Update<>().exec("deleteStatus", entity, filters);
    } else {
      throw new MVCException("The tombstone must inherit reEntity");
    }
  }

  @Override
  public int heal(long... ids) {
    if (ids.length == 1) {
      return heal(Filter.eq("id", ids[0]));
    } else if (ids.length > 1) {
      return heal(Filter.in("id", ids));
    } else {
      return heal(Filter.notNull("id"));
    }
  }

  @Override
  public int heal(Filter... filters) {
    if (entityClass.isAssignableFrom(ReEntityImpl.class)) {
      BaseEntityImpl entity;
      try {
        entity = entityClass.newInstance();
        ReEntityImpl reEntity = (ReEntityImpl) entity;
        reEntity.setDeleteStatus(false);
      } catch (InstantiationException | IllegalAccessException e) {
        throw new MVCException(e);
      }
      return new Update<>().exec("deleteStatus", entity, filters);
    } else {
      throw new MVCException("The tombstone must inherit reEntity");
    }
  }

  @Override
  public Query getNamedQuery(String name) {
    return getSession().getNamedQuery(name);
  }

  public SQLQuery executeSql(String sql) {
    return getSession().createSQLQuery(sql);
  }
}
