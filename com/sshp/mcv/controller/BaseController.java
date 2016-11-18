package com.sshp.mcv.controller;

import com.sshp.config.SpringBean;
import com.sshp.core.model.dto.result.JsonResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.mcv.manage.MvcManage;
import com.sshp.mcv.service.BaseService;
import com.sshp.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 内容摘要 ：基础控制器
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public abstract class BaseController<T extends BaseEntityImpl> extends MvcManage<T> {
  protected ThreadLocal<ModelMap> modelMapThread = new ThreadLocal<>();//视图结果数据线程池
  protected ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<>();//request线程池
  protected ThreadLocal<HttpServletResponse> responseThread = new ThreadLocal<>();//response线程池
  protected BaseService<T> service;

  protected JsonResult json() {
    return new JsonResult();
  }

  protected JsonResult json(String error) {
    return new JsonResult(false, error);
  }

  protected HttpServletRequest request() {
    return requestThread.get();
  }

  protected HttpSession session() {
    return request().getSession();
  }

  protected HttpServletResponse response() {
    return responseThread.get();
  }

  protected String getParameter(String key) {
    return request().getParameter(key);
  }

  protected ModelMap model() {
    return modelMapThread.get();
  }

  protected void addModel(String key, Object val) {
    modelMapThread.get().put(key, val);
  }

  protected String getRequestIp() {
    String ip = request().getHeader("x-forwarded-for");
    if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
      ip = request().getRemoteAddr();
    }
    if (ip != null && ip.length() > 15) {
      int index = ip.indexOf(',');
      if (index > 0) {
        ip = ip.substring(0, index);
      }
    }
    return ip;
  }

  @InitBinder
  protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
    binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        Date date = DateUtil.smartFormat(text);
        setValue(date);
      }
    });
  }
}
