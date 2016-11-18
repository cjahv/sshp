package com.sshp.plugins.hibernate.select;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;

import java.util.HashMap;
import java.util.Map;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/9/9
 */
public class GenerateKey {
  Criteria criteria;
  public Map<String, Integer> aliasMap = new HashMap<>();
  public Map<String, String> aliasSourMap = new HashMap<>();
  Map<String, JoinType> joinType;
  private int aliasIndex;

  String generateAlias(String key) {
    if (StringUtils.isEmpty(key)) return key;
    int lastSplit = key.lastIndexOf('.');
    if (lastSplit == -1) return key;
    String firstKey = key.substring(0, lastSplit);
    String lastKey = key.substring(lastSplit + 1);
    String[] keyArray = StringUtils.split(firstKey, '.');
    String alias = "";
    for (String tmpKey : keyArray) {
      alias = getAlias(mergeAlias(alias, tmpKey));
    }
    return mergeAlias(alias, lastKey);
  }

  private String mergeAlias(String alias, String key) {
    if (alias == null || alias.length() == 0) return key;
    return alias + '.' + key;
  }

  private int getAliasIndex(String key) {
    Integer alias = aliasMap.get(key);
    if (alias == null) {
      criteria.createAlias(key, generateAlias(++aliasIndex), joinType(key));
      aliasMap.put(key, aliasIndex);
      return aliasIndex;
    } else return alias;
  }

  private JoinType joinType(String name) {
    if (joinType == null) return JoinType.LEFT_OUTER_JOIN;
    if (name.indexOf('.') > 0) {
      name = revertAlias(name);
    }
    JoinType joinType = this.joinType.get(name);
    return joinType == null ? JoinType.LEFT_OUTER_JOIN : joinType;
  }

  private String revertAlias(String name) {
    if (name != null && name.length() > 2 && name.charAt(0) == '_' && name.charAt(1) == 'S') {
      int split = name.indexOf('.');
      Integer index = Integer.parseInt(name.substring(2, split));
      String subName = name.substring(split);
      for (String target : aliasMap.keySet()) {
        if (index.equals(aliasMap.get(target))) {
          return revertAlias(target + subName);
        }
      }
    }
    return name;
  }

  private String getAlias(String key) {
    return generateAlias(getAliasIndex(key));
  }

  public static String generateAlias(int index) {
    return "_S" + index;
  }
}
