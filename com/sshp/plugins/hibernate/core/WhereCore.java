package com.sshp.plugins.hibernate.core;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.select.GenerateFilter;
import com.sshp.utils.DateUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 内容摘要 ：Filter条件解析
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/14
 */
public abstract class WhereCore<T extends BaseEntityImpl> extends KeyCore<T> {

  public WhereResult buildFilter2Hql(Filter... filters) {
    StringBuilder builder = new StringBuilder();
    Map<String, Object> param = new HashMap<>();
    boolean first = true;
    for (Filter filter : filters) {
      if (filter == null || !GenerateFilter.typeCheck(filter)) continue;
      if (!first) builder.append(" and ");
      else first = false;
      String name = filter.getName();
      String alisName = getAlisName(name);
      Object value = filter.getValue();
      String not = "";
      switch (filter.getTerm()) {
        case eq:
          builder.append(name).append(" = :").append(alisName);
          param.put(alisName, value);
          break;
        case notEQ:
          builder.append(name).append(" <> :").append(alisName);
          param.put(alisName, value);
          break;
        case eqDate:
          builder.append(name).append(" BETWEEN ").append(alisName).append("0 and ").append(alisName).append("1");
          param.put(alisName + 0, DateUtil.formatBegin(value));
          param.put(alisName + 1, DateUtil.formatEnd(value));
          break;
        case ge:
          builder.append(name).append(" >= :").append(alisName);
          param.put(alisName, value);
          break;
        case gt:
          builder.append(name).append(" > :").append(alisName);
          param.put(alisName, value);
          break;
        case le:
          builder.append(name).append(" <= :").append(alisName);
          param.put(alisName, value);
          break;
        case lt:
          builder.append(name).append(" < :").append(alisName);
          param.put(alisName, value);
          break;
        case like:
          builder.append(name).append(" like :").append(alisName);
          param.put(alisName, "%" + value + "%");
          break;
        case StartLike:
          builder.append(name).append(" like :").append(alisName);
          param.put(alisName, value + "%");
          break;
        case EndLike:
          builder.append(name).append(" like :").append(alisName);
          param.put(alisName, "%" + value);
          break;
        case notIN:
          not = "not ";
        case in:
          builder.append(name).append(" ").append(not).append("in (");
          Iterator integer = ((Iterable) value).iterator();
          int i = 0;
          while (integer.hasNext()) {
            builder.append(':').append(alisName).append(i);
            param.put(alisName + i, integer.next());
            i++;
          }
          break;
        case notNULL:
          builder.append(name).append(" is not null");
          break;
        case NULL:
          builder.append(name).append(" is null");
          break;
        case empty:
          builder.append(name).append(" = ''");
          break;
        case notEmpty:
          builder.append(name).append(" <> ''");
          break;
        case betweenDate:
        case between:
          builder.append(name).append(" BETWEEN ").append(alisName).append("0 and ").append(alisName).append("1");
          param.put(alisName + 0, ((Object[]) value)[0]);
          param.put(alisName + 1, ((Object[]) value)[1]);
          break;
        case or:
          WhereResult result = buildFilter2Hql((Filter[]) value);
          builder.append(" or (").append(result.getWhereStr()).append(")");
          param.putAll(result.getParam());
          break;
      }
    }
    return new WhereResult(builder.toString(), param);
  }

  private String getAlisName(String name) {
    if (name.indexOf('.') >= 0) {
      StringBuilder buffer = new StringBuilder(name.length());
      for (int i = 0; i < name.length(); i++) {
        if (i > 0 && name.charAt(i - 1) == '.') {
          buffer.append(name.charAt(i) > 96 ? name.charAt(i) - 32 : name.charAt(i));
        } else if (name.charAt(i) != '.') {
          buffer.append(name.charAt(i));
        }
      }
      return buffer.toString();
    }
    return name;
  }
}
