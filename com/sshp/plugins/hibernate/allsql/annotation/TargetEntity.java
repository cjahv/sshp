package com.sshp.plugins.hibernate.allsql.annotation;

import java.lang.annotation.*;

/**
 * sql 实体映射
 * Created by jahv on 2016/11/23.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TargetEntity {
  String name();
  Class<?> ws();
}
