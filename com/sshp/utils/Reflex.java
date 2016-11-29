package com.sshp.utils;


import com.sshp.core.exception.InsideException;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * 内容摘要 ：反射工具类
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年01月28日
 */
public class Reflex {
  private static Map<Class, Method[]> methodCache = new HashMap<>();

  /**
   * 获取类的所有私有字段
   */
  public static Field[] getFields(Class c) {
    return c.getDeclaredFields();
  }

  /**
   * 获取实体的所有私有字段
   */
  public static Field[] getSuperFields(Class superClass) {
    List<Field> fieldList = new ArrayList<>();
    Field[] fields = getFields(superClass);
    for (superClass = superClass.getSuperclass(); !superClass.equals(Object.class); superClass = superClass.getSuperclass()) {
      Collections.addAll(fieldList, getFields(superClass));
    }
    return ArrayUtils.addAll(fields, fieldList.toArray(new Field[fieldList.size()]));
  }

  /**
   * 通过字段名获取get方法
   */
  public static Method getGetMethod(String fieldName, Class c) {
    Method[] methods = methodCache.computeIfAbsent(c, k -> c.getMethods());
    Set<Method> set = new HashSet<>();
    String isName = "is" + StringUtil.updateInitial(fieldName);
    String getName = "get" + StringUtil.updateInitial(fieldName);
    for (Method method : methods) {
      if (method.getName().equals(isName) || method.getName().equals(getName)) {
        return method;
      }
    }
    throw new InsideException("没有找到像" + fieldName + "的get方法");
  }

  /**
   * 通过字段名获取set方法
   */
  public static Method getSetMethod(String fieldName, Class c) {
    Method[] methods = methodCache.computeIfAbsent(c, k -> c.getMethods());
    Set<Method> methods1 = new HashSet<>();
    for (Method method : methods) {
      if (method.getName().equals("set" + StringUtil.updateInitial(fieldName))) {
        methods1.add(method);
      }
    }
    if(methods1.size()==1) return methods1.iterator().next();
    Iterator<Method> iterator = methods1.iterator();
    while (iterator.hasNext()) {
      Type[] types = iterator.next().getGenericParameterTypes();
      if(types.length!=1)iterator.remove();
      else if(Object.class.equals(types[0]))iterator.remove();
    }
    if(methods1.size()==1) return methods1.iterator().next();
    throw new InsideException(methods1.size() > 1 ? "找到了多个 set 方法" : "没有找到 set 方法");
  }

  /**
   * 通过字段名获取方法
   */
  public static Method getModel(Class c, String fieldName, Class<?>... classes) {
    try {
      return c.getMethod(fieldName, classes);
    } catch (NoSuchMethodException e) {
      throw new InsideException("系统错误,错误的调用:" + fieldName, e);
    }
  }

  /**
   * 通过字段名调用对象的get方法取值
   *
   * @param val       被调用的对象
   * @param fieldName 字段名
   */
  public static Object getValue(Object val, String fieldName) {
    return invoke(getGetMethod(fieldName, val.getClass()), val);
  }

  /**
   * 通过字段名调用对象的set方法赋值
   *
   * @param val       被调用的对象
   * @param fieldName 字段名
   */
  public static Object setValue(Object val, String fieldName, Object... objects) {
    Method method = getSetMethod(fieldName, val.getClass());
    if (method == null) throw new InsideException("没有获取到set方法:" + fieldName);
    Type[] types = method.getGenericParameterTypes();
    for (int i = 0; i < types.length; i++) {
      Type type = types[i];
      if (type instanceof ParameterizedType) {
        Object sub = getValue(val, fieldName);
        if (sub != null) {
          Method method1 = getModel(sub.getClass(), "add", Object.class);
          if (method1 != null) {
            invoke(method1, sub, objects);
            objects = new Object[]{sub};
          } else {
            throw new InsideException("泛型实现对象没有add方法");
          }
        } else {
          throw new InsideException("不支持set方法泛型实现");
        }
      } else if(Enum.class.isAssignableFrom((Class<?>) type)){
        Enum[] ena = ((Class<Enum>) type).getEnumConstants();
        if (objects[i] instanceof Number) {
          objects[i] = ena[(int) objects[i]];
        } else if (objects[i] instanceof String) {
          for (Enum en : ena) {
            if(en.name().equals(objects[i])){
              objects[i] = en;
              break;
            }
          }
        }
      } else {
        if (!objects[i].getClass().equals(type) && Reflex.isWrapClass((Class) type)) {
          if (type.equals(int.class) || type.equals(Integer.class)) {
            objects[i] = Convert.toInt(objects[i]);
          } else if (type.equals(long.class) || type.equals(Long.class)) {
            objects[i] = Convert.toLong(objects[i]);
          }
        }
      }
    }
    return invoke(method, val, objects);
  }

  /**
   * 调用对象的方法
   *
   * @param method 方法
   * @param val    被调用对象
   * @param option 参数
   */
  public static Object invoke(Method method, Object val, Object... option) {
    if (method == null) return null;
    try {
      return method.invoke(val, option);
    } catch (IllegalAccessException e) {
      throw new InsideException("没有对" + method.getName() + "的访问权", e);
    } catch (InvocationTargetException e) {
      throw new InsideException("没有对" + val.getClass().getName() + "的访问权", e);
    }
  }

  /**
   * 调用对象的私有方法
   *
   * @param method 方法
   * @param val    被调用对象
   * @param option 参数
   */
  public static Object invokePrivate(Method method, Object val, Object... option) {
    if (method == null) return null;
    try {
      method.setAccessible(true);
      return method.invoke(val, option);
    } catch (IllegalAccessException e) {
      throw new InsideException("没有对" + method.getName() + "的访问权", e);
    } catch (InvocationTargetException e) {
      throw new InsideException("没有对" + val.getClass().getName() + "的访问权", e);
    }
  }

  /**
   * 给对象的私有属性赋值
   *
   * @param object 操作对象
   * @param field  属性名
   * @param value  值
   */
  public static void set(Object object, String field, Object value) {
    set(object.getClass(), object, field, value);
  }

  /**
   * 给对象的私有属性赋值
   * @param c      目标类型
   * @param object 操作对象
   * @param field  属性名
   * @param value  值
   */
  public static void set(Class c, Object object, String field, Object value) {
    try {
      Field field1 = c.getDeclaredField(field);
      field1.setAccessible(true);
      field1.set(object, value);
    } catch (NoSuchFieldException e) {
      if(c.equals(Object.class)) throw new InsideException("没有这个属性", e);
      set(c.getSuperclass(),object, field, value);
    } catch (IllegalAccessException e) {
      throw new InsideException("写入值类型错误", e);
    }
  }

  /**
   * 获取私有属性值
   *
   * @param object 操作对象
   * @param field  属性名
   * @return 获取到的值
   */
  public static Object get(Object object, String field) {
    if (object == null) return null;
    try {
      Field field1 = object.getClass().getDeclaredField(field);
      field1.setAccessible(true);
      return field1.get(object);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
      new InstantiationException("没有这个属性!").printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 反射生成私有或共有实例
   *
   * @param tClass 实例class
   * @param param  构造参数
   * @param <T>    实例泛型
   * @return 实例
   */
  public static <T> T constructor(Class<T> tClass, Object... param) {
    try {
      Class<?>[] parameterTypes = new Class[param.length];
      for (int i = 0; i < param.length; i++) {
        parameterTypes[i] = param[i].getClass();
      }
      Constructor c = tClass.getDeclaredConstructor(parameterTypes);
      c.setAccessible(true);
      return (T) c.newInstance(param);
    } catch (Exception e) {
      throw new InsideException("不能创建:" + tClass.getName(), e);
    }
  }

  /**
   * 获取静态方法
   *
   * @param clzz       类
   * @param methodName 方法名
   * @param s1         方法参数
   * @return 方法
   */
  public static Method getStaticMethod(Class clzz, String methodName, Object... s1) {
    try {
      return clzz.getDeclaredMethod(methodName, Stream.of(s1).map(Object::getClass).toArray(Class[]::new));
    } catch (NoSuchMethodException e) {
      throw new InsideException("没有找到" + methodName + "方法", e);
    }
  }

  /**
   * 执行静态方法
   *
   * @param clzz 操作类
   * @param s    操作方法
   * @param s1   方法参数
   * @return object
   */
  public static Object invokeStaticMethod(Class clzz, String s, Object... s1) {
    try {
      Method method = getStaticMethod(clzz, s, s1);
      if (!method.isAccessible()) method.setAccessible(true);
      return method.invoke(null, (Object[]) s1);
    } catch (IllegalAccessException e) {
      throw new InsideException("没有访问权:" + s, e);
    } catch (InvocationTargetException e) {
      throw new InsideException("没有找到:" + s, e);
    }
  }

  /**
   * 判断一个类是否是基本数据类型
   *
   * @param clz 目标类
   * @return 是基本数据类型返回true, 否则返回false
   */
  public static boolean isWrapClass(Class clz) {
    if (clz.isPrimitive()) return true;
    try {
      Field field = clz.getField("TYPE");
      if (field == null) return false;
      Class aClass = (Class) field.get(null);
      return aClass != null && aClass.isPrimitive();
    } catch (Exception ignored) {
    }
    return false;
  }

}
