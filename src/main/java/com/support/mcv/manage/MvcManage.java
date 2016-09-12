package com.support.mcv.manage;

import com.support.core.model.entity.BaseEntityImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public class MvcManage<T extends BaseEntityImpl> {
    protected Class<T> entityClass;

    public MvcManage() {
        Class thisClass = getClass();
        Type genericSuperclass = thisClass.getGenericSuperclass();
        if (genericSuperclass != null && genericSuperclass instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericSuperclass;
            Type[] types = type.getActualTypeArguments();
            if (types.length > 0) {
                //noinspection unchecked
                entityClass = (Class<T>) types[0];
            }
        }

    }

    public MvcManage(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
