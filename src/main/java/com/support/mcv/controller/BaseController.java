package com.support.mcv.controller;

import com.support.config.SpringBean;
import com.support.core.model.dto.JsonResult;
import com.support.core.model.entity.BaseEntityImpl;
import com.support.mcv.manage.MvcManage;
import com.support.mcv.service.BaseService;
import com.support.utils.DateUtil;
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
    private ThreadLocal<ModelMap> modelMapThread = new ThreadLocal<ModelMap>();//视图结果数据线程池
    private ThreadLocal<JsonResult> jsonThread = new ThreadLocal<JsonResult>();//json结果线程池
    private ThreadLocal<HttpServletRequest> requestThread = new ThreadLocal<HttpServletRequest>();//request线程池
    private ThreadLocal<HttpServletResponse> responseThread = new ThreadLocal<HttpServletResponse>();//response线程池
    protected BaseService<T> service;

    @ModelAttribute
    public void setServletAPI(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        modelMapThread.set(modelMap);
        requestThread.set(request);
        responseThread.set(response);
        request.setAttribute("time", System.currentTimeMillis());
        service = SpringBean.getService(entityClass);
    }

    public JsonResult json() {
        JsonResult jsonResult = jsonThread.get();
        if (jsonResult == null) {
            jsonResult = new JsonResult();
            jsonThread.set(jsonResult);
        }
        return jsonResult;
    }

    public JsonResult json(String error) {
        return json().set(false, error);
    }

    public HttpServletRequest request() {
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
