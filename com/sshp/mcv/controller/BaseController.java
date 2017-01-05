package com.sshp.mcv.controller;

import com.sshp.core.exception.SystemException;
import com.sshp.core.model.dto.result.JsonResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.mcv.manage.MvcManage;
import com.sshp.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 内容摘要 ：基础控制器
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public abstract class BaseController<T extends BaseEntityImpl> extends MvcManage<T> {
  protected JsonResult json() {
    return new JsonResult();
  }

  protected JsonResult json(String error) {
    return new JsonResult(false, error);
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) throws Exception {
    binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        Date date = DateUtil.smartFormat(text);
        setValue(date);
      }
    });
    binder.registerCustomEditor(Boolean.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) setValue(null);
        else switch (text.charAt(0)) {
          case '1':
          case 't':
          case 'T':
            setValue(true);
            break;
          case '0':
          case 'f':
          case 'F':
            setValue(false);
            break;
          default:
            throw new SystemException("接收到非法数字请求.抛弃#6["+text+"]");
        }
      }
    });
    binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) return;
        for (int i = 0; i < text.length(); i++) {
          char c = text.charAt(i);
          if (i == 0 && c == '-') continue;
          if (c > 57 || c < 48) throw new SystemException("接收到非法数字请求.抛弃#0["+text+"]");
          if (i > 10) throw new SystemException("接收到非法数字请求.抛弃#1["+text+"]");
        }
        setValue(Integer.parseInt(text));
      }
    });
    binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) return;
        for (int i = 0; i < text.length(); i++) {
          char c = text.charAt(i);
          if (i == 0 && c == '-') continue;
          if (c > 57 || c < 48) throw new SystemException("接收到非法数字请求.抛弃#2["+text+"]");
          if (i > 19) throw new SystemException("接收到非法数字请求.抛弃#3["+text+"]");
        }
        setValue(Long.parseLong(text));
      }
    });
    binder.registerCustomEditor(Double.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) return;
        for (int i = 0; i < text.length(); i++) {
          char c = text.charAt(i);
          if (i == 0 && c == '-') continue;
          if (c > 57 || c < 48 && c != 46) throw new SystemException("接收到非法数字请求.抛弃#4["+text+"]");
        }
        setValue(Double.parseDouble(text));
      }
    });
    binder.registerCustomEditor(Float.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) return;
        for (int i = 0; i < text.length(); i++) {
          char c = text.charAt(i);
          if (i == 0 && c == '-') continue;
          if (c > 57 || c < 48 && c != 46) throw new SystemException("接收到非法数字请求.抛弃#5["+text+"]");
        }
        setValue(Float.parseFloat(text));
      }
    });
  }
}
