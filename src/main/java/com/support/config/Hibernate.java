package com.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@Component
@DependsOn("postProcessor")
public class Hibernate {
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws SQLException {
//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource);
//        Properties properties1 = new Properties();
//        properties1.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
//        properties1.setProperty("hibernate.show_sql","false");
//        localSessionFactoryBean.setHibernateProperties(properties1);
//        localSessionFactoryBean.setPackagesToScan("com.linkcubic.model.entity");
//        return localSessionFactoryBean;
//    }
}
