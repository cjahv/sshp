package com.linkcubic.model.entity;

import com.support.core.model.entity.BaseEntityImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/13
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable, BaseEntityImpl {
    private Long id;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        BaseEntity b = (BaseEntity) obj;
        return obj != null && (obj == this || obj.getClass() == this.getClass() && b.getId() != null && Objects.equals(getId(), b.getId()));
    }

    @Override
    public int hashCode() {
        if (getId() != null) return getId().hashCode();
        return super.hashCode();
    }
}
