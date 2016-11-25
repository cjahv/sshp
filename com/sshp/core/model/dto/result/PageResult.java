package com.sshp.core.model.dto.result;

import com.sshp.core.model.entity.BaseEntityImpl;

import java.util.List;

/**
 * 分页结果
 * Created by jahv on 2016/11/9.
 */
public class PageResult<T extends BaseEntityImpl> extends JsonResult<T> {
  public PageResult() {
  }

  public PageResult(List<T> list, Integer count, Integer countTail) {
    super.setList(list);
    this.count = count;
    this.countTail = countTail;
  }

  private Integer count;//条件总数
  private Integer countTail;//数据总数

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Integer getCountTail() {
    return countTail;
  }

  public void setCountTail(Integer countTail) {
    this.countTail = countTail;
  }
}
