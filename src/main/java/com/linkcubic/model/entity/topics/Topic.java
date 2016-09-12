package com.linkcubic.model.entity.topics;

import com.sshp.model.entity.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 内容摘要 ：专题
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月07日 11:01
 */
@Entity
@Table(name = "lcv2_topics")
@Where(clause = "status=1")
public class Topic extends BaseEntity {
    private String title;//标题
    private String decription;//描述
    private String banner;//横幅
    private Date date;//时间
    private Boolean status;//发布状态 true前台显示 false 前台隐藏
    private Date crateDate;//发布时间

    /**
     * 获取标题
     */
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取描述
     */
    @Basic
    @Column(name = "decription")
    public String getDecription() {
        return decription;
    }

    /**
     * 设置描述
     */
    public void setDecription(String decription) {
        this.decription = decription;
    }

    /**
     * 获取横幅
     */
    @Basic
    @Column(name = "banner")
    public String getBanner() {
        return banner;
    }

    /**
     * 设置横幅
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * 获取时间
     */
    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    /**
     * 设置时间
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取发布状态
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置发布状态
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取发布时间
     */
    @Column(name = "crate_date")
    public Date getCrateDate() {
        return crateDate;
    }

    /**
     * 设置发布时间
     */
    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }
}
