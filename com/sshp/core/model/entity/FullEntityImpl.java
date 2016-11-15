package com.sshp.core.model.entity;

import java.util.Date;

/**
 * 完整实体接口
 * Created by jahv on 2016/11/14.
 */
public interface FullEntityImpl<T extends BaseEntityImpl>{
  Date getCreateDate();

  void setCreateDate(Date createDate);

  Date getModifyDate();

  void setModifyDate(Date modifyDate);

  T getCreateUser();

  void setCreateUser(T createUser);

  T getModifyUser();

  void setModifyUser(T modifyUser);

  Class<T> userClass();
}
