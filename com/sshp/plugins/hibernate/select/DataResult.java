package com.sshp.plugins.hibernate.select;

import com.linkcubic.model.entity.Seo;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.exception.DataFilterException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容摘要 ：数据结果集
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年10月12日 15:51
 */
public class DataResult<T extends BaseEntityImpl> extends BuildEntity<T> {
  private static Logger log;
  private List list;

  public DataResult(DataFilter<T> dataFilter) {
    super(dataFilter);
    this.list = dataFilter.result();
    log = Logger.getLogger(DataResult.class.getName() + "<" + entityClass.getClass() + ">");
  }

  public List result() {
    return list;
  }

  public <K> List<K> result(Class<K> tClass) {
    return list;
  }

  public List<T> list() {
    if (this.list.size() == 0) return null;
    for (Object o : this.list) {
      if(o==null) continue;
      if(entityClass.equals(o.getClass())) return list;
      else break;
    }
    List<T> list = new ArrayList<>();
    for (Object o : result()) {
      list.add(build(o));
    }
    this.list = list;
    return list;
  }

  public T entity() {
    List list = result();
    if (list.size() == 0) return null;
    if (list.size() > 1) log.warn("getObject() result more than one!");
    if (list.get(0).getClass().equals(entityClass)) {
      //noinspection unchecked
      return (T) list.get(0);
    } else {
      return super.build(list.get(0));
    }
  }

  public Object getObject() {
    List list = result();
    if (list.size() == 0) return null;
    if (list.size() > 1) log.warn("getObject() result more than one!");
    return list.get(0);
  }

  public String getString() {
    return (String) getObject();
  }

  public String getStringValue() {
    Object result = getObject();
    return result == null ? null : result.toString();
  }

  public Integer getInteger() {
    Object result = getObject();
    return result == null ? null : Integer.valueOf(result.toString());
  }

  public int getIntegerValue() {
    Object result = getObject();
    if (result == null) throw new DataFilterException("getIntegerValue(), but result is null");
    return Integer.valueOf(result.toString());
  }

  public Long getLong() {
    Object result = getObject();
    return result == null ? null : Long.valueOf(result.toString());
  }

  public long getLongValue() {
    Object result = getObject();
    if (result == null) throw new DataFilterException("getLongValue(), but result is null");
    return Long.valueOf(result.toString());
  }

  public Float getFloat() {
    Object result = getObject();
    return result == null ? null : Float.valueOf(result.toString());
  }

  public float getFloatValue() {
    Object result = getObject();
    if (result == null) throw new DataFilterException("getFloatValue(), but result is null");
    return Float.valueOf(result.toString());
  }

  public Double getDouble() {
    Object result = getObject();
    return result == null ? null : Double.valueOf(result.toString());
  }

  public double getDoubleValue() {
    Object result = getObject();
    if (result == null) throw new DataFilterException("getDoubleValue(), but result is null");
    return Double.valueOf(result.toString());
  }
}
