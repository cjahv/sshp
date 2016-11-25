package com.sshp.plugins.hibernate.allsql.annotation;

import java.lang.annotation.*;

/**
 * sql 注解
 * Created by jahv on 2016/11/22.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SqlQuery {
  String name() default "";

  String value();
}
