package com.linkcubic.model.entity;

import com.linkcubic.model.enums.SeoType;
import com.sshp.model.entity.BaseEntity;
import com.sshp.web.exception.InsideException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/7/15
 */
@Entity
@Table(name = "lcv2_seo")
public class Seo extends BaseEntity implements Cloneable {
    private String key;
    private String regex;
    private String title;
    private String keywords;
    private String description;
    private SeoType type;


    public Seo(String title, String keywords, String description) {
        this.title = title;
        this.keywords = keywords;
        this.description = description;
    }

    public Seo() {
    }

    @Basic
    @Column(name = "`key`")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
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
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Seo clone() {
        try {
            return (Seo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InsideException(e);
        }
    }

    public SeoType getType() {
        return type;
    }

    public void setType(SeoType type) {
        this.type = type;
    }
}
