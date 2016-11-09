package com.sshp.utils;

import com.sshp.core.exception.InsideException;

/**
 * 内容摘要 ：类型转换
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月25日 23:17
 */
@SuppressWarnings("WeakerAccess")
public class Convert {
  public static Integer toInt(Object val) {
    return toInt(val, null);
  }

  public static Integer toInt(Object val, Integer def) {
    if (val == null) return def;
    if (val instanceof Long) {
      if ((Long) val > Integer.MAX_VALUE) {
        throw new InsideException("Long 类型值过大");
      }
      return ((Long) val).intValue();
    }
    if (val instanceof Double) {
      return ((Double) val).intValue();
    }
    if (val instanceof Float) {
      return ((Float) val).intValue();
    }
    try {
      return Integer.parseInt(String.valueOf(val));
    } catch (NumberFormatException e) {
      return def;
    }
  }

  public static Long toLong(Object val) {
    return toLong(val, null);
  }

  public static Long toLong(Object val, Long def) {
    if (val == null) return def;
    if (val instanceof Long) {
      return (long) val;
    }
    if (val instanceof Double) {
      return ((Double) val).longValue();
    }
    if (val instanceof Float) {
      return ((Float) val).longValue();
    }
    try {
      return Long.parseLong(String.valueOf(val));
    } catch (NumberFormatException e) {
      return def;
    }
  }
}
