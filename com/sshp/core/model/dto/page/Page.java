package com.sshp.core.model.dto.page;

import com.sshp.core.model.dto.result.PageResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.select.DataFilter;

/**
 * 内容摘要 ：分页筛选器接口
 * @author 陈佳慧
 * 创建日期 ：2016年五月12日 15:10
 */
public interface Page<T extends BaseEntityImpl> {
  /**
   * 获取条件筛选器
   *
   * @return 条件筛选器
   * @param entityClass 实体类型
   */
  DataFilter<T> filter(Class<T> entityClass);

  /**
   * 获取分页结果集
   *
   * @param data      结果
   * @param count     统计总数
   * @param countTail 全部总数
   * @return PageResult
   */
  PageResult getResult(Object data, Integer count, Integer countTail);

  /**
   * 是否需要自动统计总数
   *
   * @return boolean [true|开启自动统计][false|关闭自动统计]
   */
  boolean isAutoCount();

  /**
   * 是否需要自动统计全部数据总数
   *
   * @return boolean [true|开启自动统计][false|关闭自动统计]
   */
  boolean isAutoCountAll();
}
