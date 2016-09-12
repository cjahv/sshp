package com.linkcubic.model.entity.user.notice;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月30日 17:20
 */
@Entity
@Table(name = "lcv2_notice")
public class Notice extends BaseEntity {
    private String title;
    private String content;
    private Date createDate;

    public Notice() {
        createDate = new Date();
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "createdt")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createdt) {
        this.createDate = createdt;
    }
}
