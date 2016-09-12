package com.support.plugins.hibernate.core;

import com.support.core.model.entity.BaseEntityImpl;
import com.support.utils.Reflex;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.metadata.ClassMetadata;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/14
 */
public abstract class KeyCore extends SessionCore {

    protected <T extends BaseEntityImpl> String[] compileKeys(String keys, Class<T> entityClass) {
        if (keys.indexOf(',') > 0) keys = compileKey(keys);
        if (keys.indexOf('*') >= 0) return compileHash(StringUtils.split(keys, ','), entityClass);
        else return StringUtils.split(keys, ',');
    }

    private <T extends BaseEntityImpl> String[] compileHash(String[] keys, Class<T> entityClass) {
        Map<String, ClassMetadata> map = sessionFactory.getAllClassMetadata();
        Set<String> keySet = new HashSet<>();
        for (String key : keys) {
            if (key.charAt(key.length() - 1) == '*') {
                Class targetClass = entityClass;
                int end = key.indexOf('{');
                String[] exp = null;
                if (end >= 0) {
                    exp = StringUtils.split(key.substring(end + 1, key.indexOf('}')), ',');
                }
                if (end < 0) end = key.length() - 1;
                String parent = key.substring(0, end);
                if (parent.length() > 0) keySet.remove(parent.substring(0, parent.length() - 1));
                String[] sn = StringUtils.split(parent, '.');
                for (String aSn : sn) {
                    Method method = Reflex.getGetMethod(aSn, targetClass);
                    targetClass = method.getReturnType();
                }
                ClassMetadata classMetadata = map.get(targetClass.getName());
                if (classMetadata != null) {
                    String[] names = classMetadata.getPropertyNames();
                    String idName = classMetadata.getIdentifierPropertyName();
                    if (ArrayUtils.indexOf(exp, "id") < 0) keySet.add(parent + idName);
                    for (String name : names) {
                        if (ArrayUtils.indexOf(exp, name) >= 0) continue;
                        keySet.add(parent + name);
                    }
                }
            } else {
                keySet.add(key);
            }
        }
        return keySet.toArray(new String[keySet.size()]);
    }

    private String compileKey(String key) {
        StringBuilder sb = new StringBuilder();
        char[] name = null;
        boolean s = false;
        int d = 0;
        boolean r = false;
        for (int i = 0, j = 0; i < key.length(); i++) {
            char tmp = key.charAt(i);
            switch (tmp) {
                case ',':
                    if (!s) j = i;
                    sb.append(tmp);
                    if (name != null && d == 0) {
                        sb.append(name).append('.');
                    }
                    break;
                case '[':
                    if (s) {
                        d++;
                        r = true;
                        sb.append('[');
                        break;
                    }
                    if (j > 0) j++;
                    name = new char[i - j];
                    for (int k = j; k < i; k++) {
                        name[k - j] = key.charAt(k);
                    }
                    s = true;
                    sb.append('.');
                    break;
                case ']':
                    if (d > 0) {
                        d--;
                        sb.append(']');
                        break;
                    }
                    name = null;
                    s = false;
                    break;
                default:
                    sb.append(tmp);
                    break;
            }
        }
        if (r) {
            return compileKey(sb.toString());
        }
        return sb.toString();
    }
}
