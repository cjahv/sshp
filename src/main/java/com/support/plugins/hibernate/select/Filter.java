package com.support.plugins.hibernate.select;

import java.util.Date;
import java.util.List;

/**
 * 内容摘要 ：条件
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月12日 15:29
 */
@SuppressWarnings("WeakerAccess")
public class Filter {
    private String name;
    private Object value;
    private Term term;

    Filter(String name) {
        this.name = name;
    }

    public Filter(String name, Object value, Term term) {
        this.name = name;
        this.value = value;
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        if (value instanceof Date) return ((Date) value).getTime();
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public static Filter eq(String name, Object value) {
        return new Filter(name, value, Term.eq);
    }

    public static Filter notEQ(String name, Object value) {
        return new Filter(name, value, Term.notEQ);
    }

    public static Filter eqDate(String name, Object value) {
        return new Filter(name, value, Term.eqDate);
    }

    public static Filter ge(String name, Object value) {
        return new Filter(name, value, Term.ge);
    }

    public static Filter gt(String name, Object value) {
        return new Filter(name, value, Term.gt);
    }

    public static Filter le(String name, Object value) {
        return new Filter(name, value, Term.le);
    }

    public static Filter lt(String name, Object value) {
        return new Filter(name, value, Term.lt);
    }

    public static Filter like(String name, Object value) {
        return new Filter(name, value, Term.like);
    }

    public static Filter StartLike(String name, Object value) {
        return new Filter(name, value, Term.StartLike);
    }

    public static Filter EndLike(String name, Object value) {
        return new Filter(name, value, Term.EndLike);
    }

    public static Filter in(String name, Object value) {
        return new Filter(name, value, Term.in);
    }

    public static Filter notIN(String name, Object value) {
        return new Filter(name, value, Term.notIN);
    }

    public static Filter notNull(String name) {
        return new Filter(name, null, Term.notNULL);
    }

    public static Filter isNull(String name) {
        return new Filter(name, null, Term.NULL);
    }

    public static Filter empty(String name) {
        return new Filter(name, null, Term.empty);
    }

    public static Filter notEmpty(String name) {
        return new Filter(name, null, Term.notEmpty);
    }

    public static Filter between(String name, Object value) {
        return new Filter(name, value, Term.between);
    }

    public static Filter betweenDate(String name, Object value) {
        return new Filter(name, value, Term.betweenDate);
    }

    public static Filter or(Filter... filters) {
        if (filters == null) return null;
        return new Filter("_or", filters, Term.or);
    }

    public static Filter or(List<Filter> filters) {
        Filter[] f = new Filter[filters.size()];
        for (int i = 0; i < f.length; i++) {
            f[i] = filters.get(i);
        }
        return or(f);
    }
}
