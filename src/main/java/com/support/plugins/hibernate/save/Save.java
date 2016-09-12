package com.support.plugins.hibernate.save;

import com.support.core.model.entity.BaseEntityImpl;
import com.support.plugins.hibernate.core.WhereCore;
import com.support.utils.Reflex;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public class Save<T extends BaseEntityImpl> extends WhereCore {
    public int save(T entity, String... keys) {
        if (entity == null) return 0;
        if (entity.getId() == null) return insert(entity);
        else return upload(entity, keys);
    }

    public int save(Collection<T> list, String... keys) {
        int count = 0;
        for (T entity : list) {
            count += save(entity,keys);
        }
        return count;
    }

    public int save(BaseEntityImpl[] list, String... keys) {
        int count = 0;
        for (BaseEntityImpl entity : list) {
            //noinspection unchecked
            count += save((T) entity,keys);
        }
        return count;
    }

    public int insert(T entity) {
        getSession().save(entity);
        return 1;
    }

    public int insert(List<T> list) {
        for (T entity : list) {
            getSession().save(entity);
        }
        return list.size();
    }

    public int insert(T[] array) {
        for (T entity : array) {
            getSession().save(entity);
        }
        return array.length;
    }

    public int upload(T entity) {
        getSession().update(entity);
        return 1;
    }

    public int upload(T entity, String... key) {
        if (key.length == 1) key = compileKeys(key[0], entity.getClass());
        String hql = buildSaveHql(key, entity);
        return getSession().createQuery(hql).setProperties(entity).executeUpdate();
    }

    private String buildSaveHql(String[] keys, T entity) {
        StringBuilder sb = new StringBuilder("UPDATE ").append(entity.getClass().getSimpleName()).append(" r SET ");
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
                    if (val instanceof String && StringUtils.isEmpty((CharSequence) val)) continue;
            }
            sb.append("r.").append(key).append(" = :").append(key).append(", ");
        }
        if (sb.charAt(sb.length() - 2) == ',') sb.deleteCharAt(sb.length() - 2);
        else return null;
        sb.append("WHERE r.id = :id");
        return sb.toString();
    }
}
