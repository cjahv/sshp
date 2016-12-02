package com.sshp.plugins.hibernate.allsql;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.plugins.hibernate.allsql.annotation.AliasEntity;
import com.sshp.plugins.hibernate.allsql.annotation.HqlQuery;
import com.sshp.plugins.hibernate.allsql.annotation.SqlQuery;
import com.sshp.plugins.hibernate.allsql.annotation.TargetEntity;
import com.sshp.plugins.hibernate.core.SessionCore;
import com.sshp.utils.Reflex;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 执行查询
 * Created by jahv on 2016/11/22.
 */
public class BuildQuery extends SessionCore {
  private JoinPoint point;
  private ProxyMethodInvocation invocation;
  private SqlQuery sqlQuery;
  private HqlQuery hqlQuery;
  private TargetEntity targetEntity;
  private AliasEntity aliasEntity;
  private List list;

  public BuildQuery(JoinPoint point, ProxyMethodInvocation invocation) {
    this.point = point;
    this.invocation = invocation;
    Method method = invocation.getMethod();
    hqlQuery = method.getAnnotation(HqlQuery.class);
    sqlQuery = method.getAnnotation(SqlQuery.class);
    targetEntity = method.getAnnotation(TargetEntity.class);
    aliasEntity = method.getAnnotation(AliasEntity.class);
  }

  public Query query() {
    Query query;
    if (sqlQuery != null) query = getSession().createSQLQuery(sqlQuery.value().trim().toLowerCase());
    else query = getSession().createQuery(hqlQuery.value().trim().toLowerCase());
    return query;
  }

  public Object result() {
    if (list == null) {
      Query query = query();
      String sql = query.getQueryString();
      int pc = parameterCount(sql);
      if (pc > 0) {
        Object[] args = point.getArgs();
        for (int i = 0; i < pc; i++) {
          query.setParameter(i, args[i]);
        }
      } else {
        Map<String, Object> argsMap = nameCount(sql);
        if (argsMap != null) query.setProperties(argsMap);
      }
      if (sql.substring(0, 10).startsWith("select")) {
        String key = null;
        if (targetEntity != null) {
          key = sql.substring(7, sql.indexOf("from") - 1).trim();
          if (key.contains("*")) {
            ((SQLQuery) query).addEntity(targetEntity.ws());
            targetEntity = null;
          }
        }
        list = query.list();
        if (targetEntity != null) list = buildEntity(key, list);
      } else {
        return query.executeUpdate();
      }
    }
    return list;
  }

  private Map<String, Object> nameCount(String sql) {
    Set<String> names = new HashSet<>();
    StringBuilder sb = null;
    for (int i = 0; i < sql.length() + 1; i++) {
      char s = i == sql.length() ? ' ' : sql.charAt(i);
      switch (s) {
        case ':':
          sb = new StringBuilder();
          break;
        case '\n':
        case ' ':
          if (sb != null) {
            names.add(sb.toString());
            sb = null;
          }
          break;
        default:
          if (sb != null) sb.append(s);
      }
    }
    if (names.size() > 0) {
      LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
      String[] params = u.getParameterNames(invocation.getMethod());
      Map<String, Object> map = new HashMap<>();
      Iterator<String> iterator = names.iterator();
      Object[] args = point.getArgs();
      while (iterator.hasNext()) {
        String name = iterator.next();
        int index = ArrayUtils.indexOf(params, name);
        if (index > -1) map.put(name, args[index]);
      }
      if (map.size() > 0) return map;
    }
    return null;
  }

  private List buildEntity(String key, List list) {
    String[] keys = StringUtils.split(key, ',');
    HashMap<String, String> propertyMap = getPropertyMap(targetEntity.ws());
    HashMap<Class, HashMap<String, String>> aliasPropertyMap = new HashMap<>();
    List<Object> list1 = new ArrayList<>();
    for (Object o : list) {
      Object[] oo;
      if (keys.length == 1 && !(o instanceof Object[])) oo = new Object[]{o};
      else oo = (Object[]) o;
      Object target = Reflex.constructor(targetEntity.ws());
      for (int j = 0; j < oo.length; j++) {
        String[] kp = StringUtils.split(keys[j], '.');
        if (kp[0].equals(targetEntity.name())) {
          if ("id".equals(kp[1])) {
            Reflex.setValue(target, "id", oo[j]);
          } else {
            String m = propertyMap.get(kp[1]);
            Method method = Reflex.getSetMethod(m, targetEntity.ws());
            Type[] types = method.getGenericParameterTypes();
            if (types.length > 0 && sessionFactory.getAllClassMetadata().get(types[0].getTypeName()) != null) {
              Object o1 = Reflex.constructor((Class<?>) types[0]);
              Reflex.setValue(o1, "id", oo[j]);
              oo[j] = o1;
            }
            Reflex.setValue(target, m, oo[j]);
          }
        } else if (aliasEntity != null) {
          int ki = ArrayUtils.indexOf(aliasEntity.name(), kp[0]);
          if (ki != -1) {
            String aliasProperty = aliasEntity.as()[ki];
            Object as = getAs(target, aliasProperty);
            HashMap<String, String> alisMap = aliasPropertyMap.computeIfAbsent(as.getClass(), k -> getPropertyMap(as.getClass()));
            String m = alisMap.get(kp[1]);
            Reflex.setValue(as, m, oo[j]);
          }
        }
      }
      list1.add(target);
    }
    return list1;
  }

  private int parameterCount(String sql) {
    int c = 0;
    boolean search = true;
    for (int i = 0; i < sql.length(); i++) {
      switch (sql.charAt(i)) {
        case '\'':
          search = !search;
          break;
        case '?':
          if (search) c++;
      }
    }
    return c;
  }

  public HashMap<String, String> getPropertyMap(Class c) {
    HashMap<String, String> propertyMap = new HashMap<>();
    ClassMetadata classMetadata = sessionFactory.getAllClassMetadata().get(c.getName());
    String[] propertyNames = classMetadata.getPropertyNames();
    if (classMetadata instanceof SingleTableEntityPersister) {
      for (String propertyName : propertyNames) {
        String[] column = ((SingleTableEntityPersister) classMetadata).getPropertyColumnNames(propertyName);
        for (String aColumn : column) {
          propertyMap.put(aColumn, propertyName);
        }
      }
    }
    return propertyMap;
  }

  private Object getAs(Object target, String aliasProperty) {
    String[] aps = StringUtils.split(aliasProperty, '.');
    return getVal(target, aps, 0);
  }

  private Object getVal(Object target, String[] a, int i) {
    Class c = target.getClass();
    Method method = Reflex.getGetMethod(a[i], c);
    Object v = Reflex.invoke(method, target);
    if (v == null) {
      v = Reflex.constructor(method.getReturnType());
      Reflex.setValue(target, a[i], v);
    }
    if (a.length - 1 == i) return v;
    return getVal(v, a, i + 1);
  }

}
