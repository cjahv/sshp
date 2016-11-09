package com.sshp.mcv.entity;

/**
 * 逻辑删除接口
 * Created by jahv on 2016/11/2.
 */
public interface ReEntityImpl {
  /**
   * 逻辑删除字段
   *
   * @return boolean
   * <table>
   *   <tr>
   *     <td>true</td>
   *     <td>已删除</td>
   *   </tr>
   *   <tr>
   *     <td>false</td>
   *     <td>未删除</td>
   *   </tr>
   * </table>
   */
  boolean getDeleteStatus();
  /**
   * 逻辑删除字段
   *
   * @param deleteStatus boolean
   * <table>
   *   <tr>
   *     <td>true</td>
   *     <td>已删除</td>
   *   </tr>
   *   <tr>
   *     <td>false</td>
   *     <td>未删除</td>
   *   </tr>
   * </table>
   */
  void setDeleteStatus(boolean deleteStatus);
}
