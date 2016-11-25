package com.sshp.plugins.hibernate.allsql.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * hql 查询
 * Created by jahv on 2016/11/22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface HqlQuery {
  String name() default "";

  String value();
}
