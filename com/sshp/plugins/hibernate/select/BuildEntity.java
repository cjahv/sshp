package com.sshp.plugins.hibernate.select;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.utils.Reflex;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年10月12日 16:43
 */
abstract class BuildEntity<T extends BaseEntityImpl> {
  private String[] keys;
  Class<T> entityClass;

  BuildEntity(DataFilter<T> dataFilter) {
    this.entityClass = dataFilter.entityClass;
    this.keys = dataFilter.getKeys();
  }

  T build(Object val) {
    if (val == null) return null;
    T entity = Reflex.constructor(entityClass);
    Object[] values;
    if (keys.length == 1) values = new Object[]{val};
    else values = (Object[]) val;
    for (int i = 0; i < keys.length; i++) {
      if (values[i] == null) continue;
      String key = keys[i];
      if (key.indexOf(':') > 0) key = key.substring(0, key.indexOf(':'));
      if (key.indexOf('.') > 0) {
        String[] key2 = StringUtils.split(key, '.');
        T e = entity;
        int j = 0;
        do {
          Method method = Reflex.getGetMethod(key2[j], e.getClass());
          Object v1 = Reflex.invoke(method, e);
          Class returnClass = method.getReturnType();
          if (v1 != null && returnClass.isEnum()) {
            if (v1.getClass().isAssignableFrom(Integer.class)) {
              v1 = returnClass.getEnumConstants()[(int) v1];
            }
          } else if (v1 == null && BaseEntityImpl.class.isAssignableFrom(returnClass)) {
            v1 = Reflex.constructor(returnClass);
          } else if (!(v1 instanceof BaseEntityImpl)) {
            v1 = values[i];
          }
          Reflex.setValue(e, key2[j], v1);
          if (v1 instanceof BaseEntityImpl) e = (T) v1;
        } while (j++ < key2.length - 1);
      } else {
        Reflex.setValue(entity, key, values[i]);
      }
    }
    return entity;
  }
}
