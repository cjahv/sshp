package com.linkcubic.model.entity.article;

import com.sshp.model.entity.BasisEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月05日 16:22
 */
@Entity
@Table(name = "lc_category")
public class Category extends BasisEntity {
    private String name;//类别名称
    private Long parentId;//父级引用
    private List<Category> child;
    private String code;//类别code

    /**
     * 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 类别名称
     */
    public void setName(String name) {
        this.name = name;
    }

//    /**
//     * 父级引用
//     */
//    @ManyToOne
//    @JoinColumn(name = "parent_id")
//    public Category getParent() {
//        return parent;
//    }
//
//    /**
//     * 父级引用
//     */
//    public void setParent(Category parent) {
//        this.parent = parent;
//    }

    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Transient
    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

    /**
     * 类别code
     */
    public String getCode() {
        return code;
    }

    /**
     * 类别code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
