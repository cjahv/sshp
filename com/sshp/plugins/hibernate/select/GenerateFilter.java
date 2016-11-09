package com.sshp.plugins.hibernate.select;

import com.sshp.plugins.hibernate.core.filter.Filter;
import com.sshp.utils.DateUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/9/9
 */
public class GenerateFilter extends GenerateKey {

  Criterion buildFilter(Filter filter) {
    if (filter == null || !typeCheck(filter)) return null;
    Criterion criterion = null;
    String name = generateAlias(filter.getName());
    switch (filter.getTerm()) {
      case eq:
        criterion = Restrictions.eq(name, filter.getValue());
        break;
      case notEQ:
        criterion = Restrictions.ne(name, filter.getValue());
        break;
      case eqDate:
        criterion = Restrictions.between(name, DateUtil.formatBegin(filter.getValue()), DateUtil.formatEnd(filter.getValue()));
        break;
      case ge:
        criterion = Restrictions.ge(name, filter.getValue());
        break;
      case gt:
        criterion = Restrictions.gt(name, filter.getValue());
        break;
      case le:
        criterion = Restrictions.le(name, filter.getValue());
        break;
      case lt:
        criterion = Restrictions.lt(name, filter.getValue());
        break;
      case like:
        criterion = Restrictions.like(name, filter.getValue().toString(), MatchMode.ANYWHERE);
        break;
      case StartLike:
        criterion = Restrictions.like(name, filter.getValue().toString(), MatchMode.START);
        break;
      case EndLike:
        criterion = Restrictions.like(name, filter.getValue().toString(), MatchMode.END);
        break;
      case in:
        if (filter.getValue() instanceof Collection)
          criterion = Restrictions.in(name, (Collection) filter.getValue());
        else
          criterion = Restrictions.in(name, (Object[]) filter.getValue());
        break;
      case notIN:
        if (filter.getValue() instanceof Collection)
          criterion = Restrictions.not(Restrictions.in(name, (Collection) filter.getValue()));
        else
          criterion = Restrictions.not(Restrictions.in(name, (Object[]) filter.getValue()));
        break;
      case notNULL:
        criterion = Restrictions.isNotNull(name);
        break;
      case NULL:
        criterion = Restrictions.isNull(name);
        break;
      case empty:
        criterion = Restrictions.isEmpty(name);
        break;
      case notEmpty:
        criterion = Restrictions.isNotEmpty(name);
        break;
      case between:
        Object[] values = (Object[]) filter.getValue();
        criterion = Restrictions.between(name, values[0], values[1]);
        break;
      case betweenDate:
        Object[] values1 = (Object[]) filter.getValue();
        criterion = Restrictions.between(name, DateUtil.formatBegin(values1[0]), DateUtil.formatEnd(values1[1]));
        break;
      case or:
        Filter[] filter2 = (Filter[]) filter.getValue();
        List<Criterion> criterions = new ArrayList<>();
        for (Filter f : filter2) {
          Criterion criterion1 = buildFilter(f);
          if (criterion1 == null) continue;
          criterions.add(criterion1);
        }
        if (criterions.size() == 2) {
          criterion = Restrictions.or(criterions.get(0), criterions.get(1));
        } else {
          criterion = Restrictions.or(criterions.toArray(new Criterion[criterions.size()]));
        }
        break;
    }
    return criterion;
  }

  public static boolean typeCheck(Filter filter) {
    if (filter.getName().charAt(0) == '!') {
      filter.setName(filter.getName().substring(1));
      return true;
    }
    switch (filter.getTerm()) {
      case notNULL:
      case NULL:
      case empty:
      case notEmpty:
        break;
      default:
        if (filter.getValue() == null) return false;
    }
    switch (filter.getTerm()) {
      case eq:
      case notEQ:
      case like:
      case StartLike:
      case EndLike:
        //不能使用StringUtils工具类,value有可能是枚举类型
        if (filter.getValue() == null || filter.getValue().equals("")) return false;
        break;
      case eqDate:
        if (filter.getValue() instanceof String ||
                filter.getValue() instanceof Long ||
                filter.getValue() instanceof Date) break;
        return false;
      case ge:
      case gt:
      case le:
      case lt:
        if (filter.getValue() instanceof String) return false;
        break;
      case in:
      case notIN:
        if (filter.getValue() instanceof Collection || filter.getValue() instanceof Object[]) break;
        return false;
      case notNULL:
      case NULL:
      case empty:
      case notEmpty:
        break;
      case between:
        if (filter.getValue() instanceof Object[] && ((Object[]) filter.getValue()).length == 2) break;
        return false;
      case betweenDate:
        if ((filter.getValue() instanceof Date[] || filter.getValue() instanceof String[]) && ((Object[]) filter.getValue()).length == 2)
          break;
        return false;
      case or:
        if (filter.getValue() instanceof Filter[]) break;
      default:
        return false;
    }
    return true;
  }
}
