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

  public byte[] get(final Object key) {
    return (byte[]) getRedisTemplate().execute((RedisCallback) connection -> connection.get(rawKey(key)));
  }

  public int getInt(Object key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (int) val;
  }

  public long getLongValue(Object key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (long) val;
  }

  public float getFloatValue(Object key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (float) val;
  }

  public double getDoubleValue(Object key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return (double) val;
  }

  public boolean getBool(Object key) {
    Object val = get(key);
    if (val == null) throw new SshpRedisException(READ_NULL);
    return Objects.equals(true, val);
  }

  public boolean getBoolValue(Object key) {
    Object val = get(key);
    return val != null && "true".equals(val) || Objects.equals(true, val) || Objects.equals(1, val);
  }

  public Integer getInteger(Object key) {
    String result = getString(key);
    return result == null ? null : Integer.parseInt(result);
  }

  public Long getLong(Object key) {
    String result = getString(key);
    return result == null ? null : Long.parseLong(result);
  }

  public Float getFloat(Object key) {
    String result = getString(key);
    return result == null ? null : Float.parseFloat(getString(key));
  }

  public Double getDouble(Object key) {
    String result = getString(key);
    return result == null ? null : Double.parseDouble(getString(key));
  }

  public String getString(Object key) {
    byte[] result = get(key);
    return result == null ? null : new String(result);
  }

  public void set(final Object key, final Object value, final long liveTime) {
    getRedisTemplate().execute((RedisCallback) connection -> {
      byte[] k = rawKey(key);
      connection.set(k, rawHashKey(value));
      if (liveTime > 0) {
        connection.expire(k, liveTime);
      }
      return 1L;
    });
  }

  public void set(final Object key, final Object value) {
    set(key, value, 0);
  }

  public <T> T execute(RedisCallback<T> action) {
    return (T) getRedisTemplate().execute(action);
  }

  private byte[] rawKey(Object key) {
    return (base_namespace + namespaces + key).getBytes();
  }

  private <T> byte[] rawHashKey(T hashKey) {
    if (hashKey == null) return null;
    if (hashKey instanceof byte[]) {
      return (byte[]) hashKey;
    }
    if (hashKey instanceof String || Reflex.isWrapClass(hashKey.getClass())) {
      return hashKey.toString().getBytes();
    }
    return JSONObject.toJSONString(hashKey).getBytes();
  }

  private RedisTemplate getRedisTemplate() {
    if (redisTemplate == null) redisTemplate = SpringBean.getBean(RedisTemplate.class);
    return redisTemplate;
  }

  public static RedisClient byNamespaces(String namespaces) {
    return new RedisClient(namespaces);
  }

  public static void baseNamespace(String namespaces) {
    base_namespace = namespaces;
  }
}
