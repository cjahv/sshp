package com.support.config;

import com.support.mcv.dao.BaseDao;
import com.support.mcv.service.BaseService;
import org.hibernate.SessionFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Component
@DependsOn("hibernate")
public class Mvc {
    private final SessionFactory sessionFactory;
    private final PostProcessor postProcessor;

    @Autowired
    public Mvc(PostProcessor postProcessor, SessionFactory sessionFactory) {
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
                String defaultDaoName = "baseDao<" + defaultClass.getName() + ">";
                String defaultServiceName = "baseService<" + defaultClass.getName() + ">";
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
