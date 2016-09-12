package com.support.plugins.routing.entity;

import com.support.core.model.entity.BaseEntityImpl;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/24
 */
@Entity
@Table(name = "s_routing_domain")
public class Domain implements BaseEntityImpl {
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
