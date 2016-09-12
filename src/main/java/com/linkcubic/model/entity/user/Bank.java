package com.linkcubic.model.entity.user;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月23日 18:00
 */
@Entity
@Table(name = "lc_banks")
public class Bank extends BaseEntity {
    private String code;
    private String name;
    private String picurl;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "picurl")
    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
