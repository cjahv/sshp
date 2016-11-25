package com.sshp.mcv.manage;

import com.sshp.core.model.dto.page.Page;
import com.sshp.core.model.dto.result.PageResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.filter.Order;
import com.sshp.plugins.hibernate.core.impl.Condition;
import com.sshp.plugins.hibernate.select.DataFilter;
import com.sshp.plugins.hibernate.select.DataResult;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

/**
 * 内容摘要 ：基础数据层和基础业务层接口
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/29
 */
public interface BaseServiceAndDaoImpl<T extends BaseEntityImpl> {
  /**
   * 求表的总数
   *
   * @return long
   */
  Integer count();

  /**
   * 更具条件求总数
   *
   * @param filters 条件
   * @return long
   */
  Integer count(Filter... filters);

  /**
   * 根据id获取一个实体
   *
   * @param id 实体id
   * @return T 没查到返回null
   */
  T get(Long id);

  /**
   * 根据id和key获取一个实体的指定数据
   *
   * @param key 字段列表，遵循key语法
   *            <ul>
   *             <li>* 全部字段</li>
   *             <li>{xxx} 排除xxx字段，必须紧跟在*前面</li>
   *             <li>alis[key1,key2, ...] alis下面的key1,key2, ... 字段</li>
   *            </ul>
   * @param id 实体id
   * @return T 没查到返回null
   */
  T get(String key, Long id);

  /**
   * 查询所以对象的指定字段
   *
   * @param key 字段列表，遵循key语法
   *            <ul>
   *             <li>* 全部字段</li>
   *             <li>{xxx} 排除xxx字段，必须紧跟在*前面</li>
   *             <li>alis[key1,key2, ...] alis下面的key1,key2, ... 字段</li>
   *            </ul>
   * @return 数据结果集
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(String key);

  /**
   * 查询指定条件的数据
   *
   * @param filters 条件列表
   * @return 数据结果集
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(Filter... filters);

  /**
   * 查询指定条件对象的指定字段
   *
   * @param key     字段列表，遵循key语法
   *                <ul>
   *                 <li>* 全部字段</li>
   *                 <li>{xxx} 排除xxx字段，必须紧跟在*前面</li>
   *                 <li>alis[key1,key2, ...] alis下面的key1,key2, ... 字段</li>
   *                </ul>
   * @param filters 条件列表
   * @return 数据结果集
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(String key, Filter... filters);

  /**
   * 查询所以对象
   *
   * @param orders 排序方式
   * @return 数据结果集
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(Order... orders);

  /**
   * 查询所以对象的指定字段
   *
   * @param key    字段列表，遵循key语法
   *               <ul>
   *                <li>* 全部字段</li>
   *                <li>{xxx} 排除xxx字段，必须紧跟在*前面</li>
   *                <li>alis[key1,key2, ...] alis下面的key1,key2, ... 字段</li>
   *               </ul>
   * @param orders 排序方式
   * @return 数据结果集
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(String key, Order... orders);

  /**
   * 查询指定对象的指定字段
   *
   * @param key     字段列表，遵循key语法
   *                <ul>
   *                 <li>* 全部字段</li>
   *                 <li>{xxx} 排除xxx字段，必须紧跟在*前面</li>
   *                 <li>alis[key1,key2, ...] alis下面的key1,key2, ... 字段</li>
   *                </ul>
   * @param objects 条件列表<br>
   *                <ul>
   *                  <li>Filter 查询条件</li>
   *                  <li>Order 排序条件</li>
   *                  <li>Other 其他条件</li>
   *                </ul>
   * @return 数据结果集
   * @see Filter
   * @see com.sshp.plugins.hibernate.core.filter.Order
   * @see com.sshp.plugins.hibernate.core.filter.Other
   * @see Condition
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(String key, Condition... objects);


  /**
   * 查询指定对象
   *
   * @param objects 条件列表<br>
   *                <ul>
   *                 <li>Filter 查询条件</li>
   *                 <li>Order 排序条件</li>
   *                 <li>Other 其他条件</li>
   *                </ul>
   * @return 数据结果集
   * @see Filter
   * @see com.sshp.plugins.hibernate.core.filter.Order
   * @see com.sshp.plugins.hibernate.core.filter.Other
   * @see Condition
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(Condition... objects);

  /**
   * 自定义查询
   *
   * @param filter DataFilter
   * @return 数据结果集
   * @see DataFilter
   * @see com.sshp.plugins.hibernate.select.DataResult
   */
  DataResult<T> get(DataFilter<T> filter);

  /**
   * 获取分页数据
   * @param page 分页对象
   * @return 分页结果集
   * @see PageResult
   */
  PageResult<T> get(Page page);

  /**
   * 保存一个对象
   *
   * @param entity 对象实体
   * @return 保存影响数据行数
   */
  int save(T entity);

  /**
   * 保存一个对象的指定字段
   *
   * @param keys   字段列表
   * @param entity 对象实体
   * @return 保存影响数据行数
   */
  int save(String keys, T entity);

  /**
   * 删除对象
   *
   * @param ids 删除的id数组
   * @return 删除影响数据行数
   */
  int delete(long... ids);

  /**
   * 按条件删除对象
   *
   * @param filters 删除条件
   * @return 删除影响数据行数
   */
  int delete(Filter... filters);

  /**
   * 逻辑删除对象
   *
   * @param ids 删除的id数组
   * @return 删除影响数据行数
   */
  int logicDelete(long... ids);

  /**
   * 按条件逻辑删除对象
   *
   * @param filters 删除条件
   * @return 删除影响数据行数
   */
  int logicDelete(Filter... filters);

  /**
   * 还原逻辑删除的数据
   *
   * @param ids 还原的id数组
   * @return 还原影响数据行数
   */
  int heal(long... ids);

  /**
   * 还原逻辑删除的数据
   *
   * @param filters 还原条件
   * @return 还原影响数据行数
   */
  int heal(Filter... filters);

  /**
   * 通过配置文件获取查询
   */
  Query getNamedQuery(String name);

  /**
   * 执行 sql
   * @param sql 目标 sql
   * @return SQLQuery
   * @see SQLQuery
   */
  SQLQuery executeSql(String sql);
}
