package com.support.plugins.hibernate.core.criterion;

import com.support.plugins.hibernate.select.GenerateKey;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Projection;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.type.Type;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 内容摘要 ：sql运行使用多个别名
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月30日 18:48
 */
public class SQLProjection implements Projection {
    private final String sql;
    private final String groupBy;
    private final Type[] types;
    private String[] aliases;
    private String[] columnAliases;
    private boolean grouped;

    private Map<String, Integer> alias;

    SQLProjection(String sql, String[] columnAliases, Type[] types) {
        this(sql, null, columnAliases, types);
    }

    SQLProjection(String sql, String groupBy, String[] columnAliases, Type[] types) {
        this.sql = sql;
        this.types = types;
        this.aliases = columnAliases;
        this.columnAliases = columnAliases;
        this.grouped = groupBy != null;
        this.groupBy = groupBy;
    }

    @Override
    public String toSqlString(Criteria criteria, int loc, CriteriaQuery criteriaQuery) {
        return replace(sql, criteria, criteriaQuery);
    }

    @Override
    public String toGroupSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
        return replace(groupBy, criteria, criteriaQuery);
    }

    private String replace(String sql, Criteria criteria, CriteriaQuery criteriaQuery) {
        Map<String, String> alias = new HashMap<>();
        this.alias.keySet().iterator().forEachRemaining(item -> alias.put(GenerateKey.generateAlias(this.alias.get(item)), item));
        CriteriaImpl rootCriteria;
        if (criteria instanceof CriteriaImpl) {
            rootCriteria = (CriteriaImpl) criteria;
        } else if (criteria instanceof CriteriaImpl.Subcriteria) {
            rootCriteria = (CriteriaImpl) ((CriteriaImpl.Subcriteria) criteria).getParent();
        } else {
            throw new HibernateException("暂不支持其他Criteria的实现");
        }
        Iterator iterateSubCriteria = rootCriteria.iterateSubcriteria();
        String tempSql = sql;
        while (iterateSubCriteria.hasNext()) {
            CriteriaImpl.Subcriteria subCriteria = (CriteriaImpl.Subcriteria) iterateSubCriteria.next();
            tempSql = StringHelper.replace(tempSql, "{" + alias.get(subCriteria.getAlias()) + "}", criteriaQuery.getSQLAlias(subCriteria));
        }

        return StringHelper.replace(tempSql, "{alias}", criteriaQuery.getSQLAlias(criteria));
    }

    public void setAlias(Map<String, Integer> alias) {
        this.alias = alias;
    }

    @Override
    public Type[] getTypes(Criteria crit, CriteriaQuery criteriaQuery) {
        return types;
    }

    @Override
    public String toString() {
        return sql;
    }

    @Override
    public String[] getAliases() {
        return aliases;
    }

    @Override
    public String[] getColumnAliases(int loc) {
        return columnAliases;
    }

    @Override
    public boolean isGrouped() {
        return grouped;
    }

    @Override
    public Type[] getTypes(String alias, Criteria crit, CriteriaQuery criteriaQuery) {
        return null;
    }

    @Override
    public String[] getColumnAliases(String alias, int loc) {
        return null;
    }
}
