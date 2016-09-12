package com.support.config;

import com.support.plugins.routing.RoutingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/24
 */
@Configuration
public class FilterConfig {
    @Bean
    public RoutingFilter routingFilter() {
        return new RoutingFilter();
    }
}
