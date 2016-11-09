package com.sshp.plugins.hibernate.core.filter;

import com.sshp.plugins.hibernate.core.impl.Condition;

import java.util.Arrays;

/**
 * 其他条件
 * Created by jahv on 2016/11/1.
 */
public class Other implements Condition {
  public static final Other one = new Other(0);
  public static final Other all = new Other(1);

  public Other(int index, Object... args) {
    this.index = index;
    this.args = args;
  }

  private int index;
  private Object[] args;

  public int getIndex() {
    return index;
  }

  public Object getArgs(int index) {
    return args[index];
  }

  public <T> T getArgs(Class<T> tClass) {
    for (Object o : args) {
      if (o.getClass().equals(tClass)) return (T) o;
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Other other = (Other) o;

    return index == other.index;
  }

  @Override
  public int hashCode() {
    int result = index;
    result = 31 * result + Arrays.hashCode(args);
    return result;
  }
}
