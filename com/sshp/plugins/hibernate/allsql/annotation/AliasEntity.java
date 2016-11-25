package com.sshp.plugins.hibernate.allsql.annotation;

import java.lang.annotation.*;

/**
 * Created by jahv on 2016/11/23.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AliasEntity {
  String[] name();
  String[] as();
}
