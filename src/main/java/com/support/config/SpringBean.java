package com.support.config;

import com.support.core.model.entity.BaseEntityImpl;
import com.support.mcv.dao.BaseDao;
import com.support.mcv.service.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Component
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
        //noinspection unchecked
        return (BaseService<T>) getBean("baseService<" + tClass.getName() + ">");
    }

    public static <T extends BaseEntityImpl> BaseDao<T> getDao(Class<T> tClass) {
        //noinspection unchecked
        return (BaseDao<T>) getBean("baseDao<" + tClass.getName() + ">");
    }
}
