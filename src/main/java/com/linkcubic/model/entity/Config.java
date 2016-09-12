package com.linkcubic.model.entity;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 内容摘要 ：程序配置实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月17日 11:45
 */
@Entity
@Table(name = "lcv2_config")
public class Config extends BaseEntity {
    private String key;
    private String value;

    @Column(name = "\"key\"")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
