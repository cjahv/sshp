package com.sshp.core.model.dto.result;

/**
 * 分页结果
 * Created by jahv on 2016/11/9.
 */
public class PageResult extends JsonResult {
  public PageResult() {
  }

  public PageResult(Object data,Integer count, Integer countTail) {
    super.setData(data);
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
