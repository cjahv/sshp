package com.sshp.config;

import com.sshp.mcv.dao.BaseDao;
import com.sshp.mcv.service.BaseService;
import com.sshp.utils.StringUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 内容摘要 ：自动装载 hibernate 的所有 model 的 service 和 dao
 * 自动注入时 使用的变量名为 model 名首字母小写 + BaseDao/BaseService
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Component
@Lazy(false)
public class AutoConfigServiceAndDao {
  private final SessionFactory sessionFactory;
  private final PostProcessor postProcessor;

  @Autowired
  public AutoConfigServiceAndDao(PostProcessor postProcessor, SessionFactory sessionFactory) {
    this.postProcessor = postProcessor;
    this.sessionFactory = sessionFactory;
  }

  /**
   * 加载默认的service,dao
   */
  @PostConstruct
  private void loadDefaultMvcBean() {
    for (String name : sessionFactory.getAllClassMetadata().keySet()) {
      try {
        Class<?> defaultClass = Class.forName(name);
        String defaultName = StringUtil.updateInitialLower(defaultClass.getSimpleName());
        String defaultDaoName = defaultName + "BaseDao";
        String defaultServiceName = defaultName + "BaseService";
        BeanDefinitionBuilder builderDao = BeanDefinitionBuilder.rootBeanDefinition(BaseDao.class);
        builderDao.addConstructorArgValue(defaultClass);
        BeanDefinitionBuilder builderService = BeanDefinitionBuilder.rootBeanDefinition(BaseService.class);
        builderService.addConstructorArgValue(defaultClass);
        postProcessor.beanDefinitionRegistry.registerBeanDefinition(defaultDaoName, builderDao.getBeanDefinition());
        postProcessor.beanDefinitionRegistry.registerBeanDefinition(defaultServiceName, builderService.getBeanDefinition());
      } catch (ClassNotFoundException e) {
        throw new InvalidPropertyException(this.getClass(), name, "not find class: " + name, e);
      }
    }
  }
}
