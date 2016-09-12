package com.support.core.model.dto;

import com.alibaba.fastjson.JSONObject;

public class JsonResult {

    private boolean success;            //结果状态
    private String msg;                    //信息
    private Object bean;                //结果对象

    public JsonResult() {
        this.success = true;
    }

    public JsonResult(boolean success) {
        this.success = success;
    }

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResult set(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public JsonResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getBean() {
        return bean;
    }

    public JsonResult setBean(Object bean) {
//        if (bean instanceof DataResult) {
//            throw new SystemException("不能将数据集直接返回给用户!");
//        }
        this.bean = bean;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
