package com.sshp.config.hibernate;

import com.sshp.core.exception.SystemException;
import com.sshp.core.model.dto.result.JsonResult;
import com.sshp.plugins.hibernate.allsql.BuildQuery;
import com.sshp.plugins.hibernate.allsql.JoinResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * allsql 自动配置
 * Created by jahv on 2016/11/22.
 */
@Aspect
@Configuration
public class AllSqlAutoConfiguration {

  @Pointcut("@annotation(com.sshp.plugins.hibernate.allsql.annotation.HqlQuery)")
  public void hqlAspect() {
  }

  @Pointcut("@annotation(com.sshp.plugins.hibernate.allsql.annotation.SqlQuery)")
  public void sqlAspect() {
  }

  @Before("hqlAspect()")
  public void hqlAspectBefore(JoinPoint point) throws NoSuchFieldException, IllegalAccessException {
    aspectBefore(point);
  }

  @Before("sqlAspect()")
  public void sqlAspectBefore(JoinPoint point) throws NoSuchFieldException, IllegalAccessException {
    aspectBefore(point);
  }

  private void aspectBefore(JoinPoint point) throws NoSuchFieldException, IllegalAccessException {
    Class pointClass = point.getClass();
    Field field = pointClass.getDeclaredField("methodInvocation");
    field.setAccessible(true);
    ProxyMethodInvocation invocation = (ProxyMethodInvocation) field.get(point);
    BuildQuery buildQuery = new BuildQuery(point, invocation);
    Object[] args = point.getArgs();
    for (int i = args.length - 1; i >= 0; i--) {
      if (args[i] == null || args[i] instanceof JoinResult) {
        JoinResult joinResult = args[i] == null ? new JoinResult() : (JoinResult) args[i];
        joinResult.setResult(buildQuery.result());
        args[i] = joinResult;
        return;
      }
    }
    throw new SystemException("无法找到注入参数");
  }
}
