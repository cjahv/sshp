package com.sshp.config;

import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.mcv.dao.BaseDao;
import com.sshp.mcv.service.BaseService;
import com.sshp.utils.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 内容摘要 ：
 * <dl>
 *   <dt>spring bean 管理工具</dt>
 *   <dd>需要提前注入到spring</dd>
 *   <dd>依赖StringUtil工具类</dd>
 * </dl>
 * @see StringUtil
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@SuppressWarnings("unchecked")
@Component
@Lazy(false)
public class SpringBean implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    SpringBean.applicationContext = applicationContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public static <T> T getBean(Class<T> tClass) {
    return applicationContext.getBean(tClass);
  }

  public static Object getBean(String beanName) {
    return applicationContext.getBean(beanName);
  }

  public static <T extends BaseEntityImpl> BaseService<T> getService(Class<T> tClass) {
    if (tClass == null || !BaseEntityImpl.class.isAssignableFrom(tClass))
      return (BaseService<T>) getBean(applicationContext.getBeanNamesForType(BaseService.class)[0]);
    return (BaseService<T>) getBean(StringUtil.updateInitialLower(tClass.getSimpleName()) + "BaseService");
  }

  public static <T extends BaseEntityImpl> BaseDao<T> getDao(Class<T> tClass) {
    if (tClass == null || !BaseEntityImpl.class.isAssignableFrom(tClass))
      return (BaseDao<T>) getBean(applicationContext.getBeanNamesForType(BaseDao.class)[0]);
    return (BaseDao<T>) getBean(StringUtil.updateInitialLower(tClass.getSimpleName()) + "BaseDao");
  }
}
