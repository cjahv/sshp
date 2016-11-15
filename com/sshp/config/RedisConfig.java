package com.sshp.config;

import com.sshp.plugins.redis.RedisClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * redis 配置
 * Created by jahv on 2016/11/15.
 */
@Configuration
@ConfigurationProperties(prefix = "project.redis")
public class RedisConfig {

  @Bean
  @SuppressWarnings("SpringJavaAutowiringInspection")
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    template.afterPropertiesSet();
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    template.setValueSerializer(jackson2JsonRedisSerializer);
    template.afterPropertiesSet();
    return template;
  }

  public void setBaseNamespace(String baseNamespace) {
    RedisClient.baseNamespace(baseNamespace + ':');
  }
}
