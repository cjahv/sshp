package com.sshp.plugins.redis;

import com.alibaba.fastjson.JSONObject;
import com.sshp.config.SpringBean;
import com.sshp.utils.Reflex;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * redis 操作类
 * Created by jahv on 2016/11/15.
 */
public class RedisClient {
  private static final String READ_NULL = "read value is null";
  private static String base_namespace = "";
  private String namespaces;
  private RedisTemplate redisTemplate;

  private RedisClient(String namespaces) {
    this.namespaces = namespaces;
  }

  public byte[] get(final String key) {
    return (byte[]) getRedisTemplate().execute((RedisCallback) connection -> connection.get(rawKey(key)));
  }

  public int getInt(String key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (int) val;
  }

  public long getLongValue(String key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (long) val;
  }

  public float getFloatValue(String key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (float) val;
  }

  public double getDoubleValue(String key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (double) val;
  }

  public boolean getBool(String key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return Objects.equals(true, val);
  }

  public boolean getBoolValue(String key) {
    Object val = get(key);
    return val != null && "true".equals(val) || Objects.equals(true, val) || Objects.equals(1, val);
  }

  public Integer getInteger(String key) {
    return Integer.parseInt(getString(key));
  }

  public Long getLong(String key) {
    return Long.parseLong(getString(key));
  }

  public Float getFloat(String key) {
    return Float.parseFloat(getString(key));
  }

  public Double getDouble(String key) {
    return Double.parseDouble(getString(key));
  }

  public String getString(String key) {
    return new String(get(key));
  }

  public void set(final String key, final Object value, final long liveTime) {
    getRedisTemplate().execute((RedisCallback) connection -> {
      byte[] k = rawKey(key);
      connection.set(k, rawHashKey(value));
      if (liveTime > 0) {
        connection.expire(k, liveTime);
      }
      return 1L;
    });
  }

  public void set(final String key, final Object value) {
    set(key, value, 0);
  }

  public <T> T execute(RedisCallback<T> action){
    return (T) getRedisTemplate().execute(action);
  }

  private byte[] rawKey(String key) {
    return (base_namespace + namespaces + key).getBytes();
  }

  private <T> byte[] rawHashKey(T hashKey) {
    if(hashKey==null) return null;
    if (hashKey instanceof byte[]) {
      return (byte[]) hashKey;
    }
    if (hashKey instanceof String || Reflex.isWrapClass(hashKey.getClass())) {
      return hashKey.toString().getBytes();
    }
    return JSONObject.toJSONString(hashKey).getBytes();
  }

  private RedisTemplate getRedisTemplate() {
    if(redisTemplate==null) redisTemplate = SpringBean.getBean(RedisTemplate.class);
    return redisTemplate;
  }

  public static RedisClient byNamespaces(String namespaces) {
    return new RedisClient(namespaces);
  }

  public static void baseNamespace(String namespaces) {
    base_namespace = namespaces;
  }
}
