package com.sshp.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 内容摘要 ：获取BeanDefinitionRegistry
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Component
@Lazy(false)
class PostProcessor implements BeanDefinitionRegistryPostProcessor {
  BeanDefinitionRegistry beanDefinitionRegistry;

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
    this.beanDefinitionRegistry = beanDefinitionRegistry;
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
  }
}
