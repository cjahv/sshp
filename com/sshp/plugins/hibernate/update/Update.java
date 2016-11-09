package com.sshp.plugins.hibernate.update;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.plugins.hibernate.core.WhereCore;
import com.sshp.plugins.hibernate.core.WhereResult;
import com.sshp.utils.Reflex;
import org.hibernate.Query;
import org.springframework.util.StringUtils;

/**
 * 内容摘要 ：更新数据类
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public class Update<T extends BaseEntityImpl> extends WhereCore {

  public int exec(T entity) {
    getSession().update(entity);
    return 1;
  }

  public int exec(String keys, T entity, Filter... filters) {
    String[] key = compileKeys(keys, entity.getClass());
    WhereResult result = filters != null && filters.length > 0 ? super.buildFilter2Hql(filters) : null;
    String hql = buildSaveHql(key, entity, result == null ? null : result.getWhereStr());
    Query query = getSession().createQuery(hql).setProperties(entity);
    if (result != null) query.setProperties(result.getParam());
    return query.executeUpdate();
  }

  private String buildSaveHql(String[] keys, T entity, String where) {
    StringBuilder sb = new StringBuilder("UPDATE ").append(entity.getClass().getSimpleName()).append(" SET ");
    for (String key : keys) {
      if ("id".equals(key)) continue;
      switch (key.charAt(0)) {
        case '!':
          key = key.substring(1);
          if (Reflex.get(entity, key) == null) continue;
          break;
        case '#':
          key = key.substring(1);
          Object val = Reflex.get(entity, key);
          if (val == null) continue;
          if (val instanceof String && StringUtils.isEmpty(val)) continue;
      }
      int d = key.lastIndexOf('+');
      if (d == -1) d = key.lastIndexOf('-');
      if (d != -1)
        sb.append(key.substring(0, d)).append(" = ").append(key).append(", ");
      else
        sb.append(key).append(" = :").append(key).append(", ");
    }
    if (sb.charAt(sb.length() - 2) == ',') sb.deleteCharAt(sb.length() - 2);
    else return null;
    if (where == null) {
      sb.append("WHERE id = :id");
    } else {
      sb.append("WHERE ").append(where);
    }
    return sb.toString();
  }
}
