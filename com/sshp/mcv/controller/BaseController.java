package com.sshp.mcv.controller;

import com.sshp.config.SpringBean;
import com.sshp.core.model.dto.JsonResult;
import com.sshp.core.model.entity.BaseEntityImpl;
import com.sshp.mcv.manage.MvcManage;
import com.sshp.mcv.service.BaseService;
import com.sshp.utils.DateUtil;
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
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
public class BaseController<T extends BaseEntityImpl> extends MvcManage<T> {
  private ThreadLocal<ModelMap> modelMapThread = new ThreadLocal<>();//视图结果数据线程池
  private ThreadLocal<JsonResult> jsonThread = new ThreadLocal<>();//json结果线程池
  private ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<>();//request线程池
  private ThreadLocal<HttpServletResponse> responseThread = new ThreadLocal<>();//response线程池
  protected BaseService<T> service;

  @ModelAttribute
  public void setServletAPI(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
    modelMapThread.set(modelMap);
    requestThread.set(request);
    responseThread.set(response);
    request.setAttribute("time", System.currentTimeMillis());
    service = SpringBean.getService(entityClass);
  }

  protected JsonResult json() {
    JsonResult jsonResult = jsonThread.get();
    if (jsonResult == null) {
      jsonResult = new JsonResult();
      jsonThread.set(jsonResult);
    } else {
      if (!jsonResult.isSuccess() || jsonResult.getMsg() != null) {
        jsonThread.set(null);
        jsonResult = new JsonResult();
      }
    }
    return jsonResult;
  }

  protected JsonResult json(String error) {
    jsonThread.set(null);
    return new JsonResult(false, error);
  }

  private HttpServletRequest request() {
    return requestThread.get();
  }

  public HttpSession session() {
    return request().getSession();
  }

  public HttpServletResponse response() {
    return responseThread.get();
  }

  public String getParameter(String key) {
    return request().getParameter(key);
  }

  public ModelMap model() {
    return modelMapThread.get();
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
