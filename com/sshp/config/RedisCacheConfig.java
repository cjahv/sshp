package com.sshp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * spring redis cache 配置
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
  private static final String prefix = "spring:cache:";

  @Bean
  public KeyGenerator wiselyKeyGenerator() {
    return (target, method, params) -> {
      StringBuilder sb = new StringBuilder(prefix);
      char[] names = target.getClass().getName().toCharArray();
      for (char name : names) {
        if (name == '.') sb.append(':');
        else sb.append(name);
      }
      sb.append(method.getName());
      sb.append('(');
      boolean first = true;
      for (Object obj : params) {
        sb.append(obj.toString());
        if (!first) sb.append(',');
        first = false;
      }
      sb.append(')');
      return sb.toString();
    };
  }


  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    return new RedisCacheManager(redisTemplate);
  }
}  