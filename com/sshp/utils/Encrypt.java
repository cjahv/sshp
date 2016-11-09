package com.sshp.utils;

import com.sshp.core.exception.InsideException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * md5加密出来的长度是32位
 * sha加密出来的长度是40位
 * base64加密可以指定字符集，可以解密
 */
public class Encrypt {

  /**
   * md5加密
   */
  public static String e(String inputText) {
    return md5(inputText);
  }

  /**
   * 解密base64
   */
  public static String d(String inputText) {
    return base64Decode(inputText);
  }

  /**
   * 使用Base64加密
   */
  public static String base64Encode(String inputText) {
    return new BASE64Encoder().encode(inputText.getBytes());
  }

  /**
   * Base64解密
   */
  public static String base64Decode(String inputText) {
    try {
      return new String(new BASE64Decoder().decodeBuffer(inputText));
    } catch (IOException e) {
      return null;
    }
  }

  /**
   * 二次加密，应该破解不了了吧？
   */
  public static String md5AndSha(String inputText) {
    return sha(md5(inputText));
  }

  /**
   * md5加密
   */
  public static String md5(String inputText) {
    return encrypt(inputText, "md5");
  }

  /**
   * sha加密
   */
  public static String sha(String inputText) {
    return encrypt(inputText, "sha-1");
  }

  public static String sha(InputStream in) {
    int size = 1048576;
    byte[] bytes = new byte[size];
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA-1");
      int len;
      while ((len = in.read(bytes)) > 0)
        md.update(bytes, 0, len);
      return String.format("%14x", new java.math.BigInteger(1, md.digest()));
    } catch (NoSuchAlgorithmException e) {
      throw new InsideException("没有找到计算类型", e);
    } catch (FileNotFoundException e) {
      throw new InsideException("没有找到计算的文件", e);
    } catch (IOException e) {
      throw new InsideException("计算文件时出错", e);
    }
  }

  /**
   * md5或者sha-1加密
   *
   * @param inputText     要加密的内容
   * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
   */
  private static String encrypt(String inputText, String algorithmName) {
    if (inputText == null) {
      throw new IllegalArgumentException("请输入要加密的内容");
    }
    if (algorithmName == null || "".equals(algorithmName.trim())) {
      algorithmName = "md5";
    }
    try {
      MessageDigest m = MessageDigest.getInstance(algorithmName);
      m.update(inputText.getBytes("UTF8"));
      byte s[] = m.digest();
      // m.digest(inputText.getBytes("UTF8"));
      return hex(s);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 返回十六进制字符串
   */
  private static String hex(byte[] arr) {
    StringBuilder sb = new StringBuilder();
    for (byte anArr : arr) {
      sb.append(Integer.toHexString((anArr & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString();
  }
}
