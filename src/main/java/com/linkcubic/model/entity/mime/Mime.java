package com.linkcubic.model.entity.mime;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 内容摘要 ：允许上传的文件类型mime
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月11日 13:37
 */
@Entity
@Table(name = "lc_mime_text")
public class Mime extends BaseEntity {
    private String mime;//mime类型

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
