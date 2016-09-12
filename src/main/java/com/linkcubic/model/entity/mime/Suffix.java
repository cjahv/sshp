package com.linkcubic.model.entity.mime;


import com.sshp.model.entity.BaseEntity;
import com.sshp.model.enums.FileType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 内容摘要 ：允许上传的文件类型
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月11日 13:35
 */
@Entity
@Table(name = "lc_mime_suffix")
public class Suffix extends BaseEntity {
    private String suffix;//文件后缀
    private Mime mime;//mime类型
    private FileType type;//生效类型

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @ManyToOne
    @JoinColumn(name = "mime_id")
    public Mime getMime() {
        return mime;
    }

    public void setMime(Mime mime) {
        this.mime = mime;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }
}
