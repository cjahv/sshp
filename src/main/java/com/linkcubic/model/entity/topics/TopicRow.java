package com.linkcubic.model.entity.topics;

import com.linkcubic.model.entity.product.Product;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * 内容摘要 ：专题详情
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月07日 11:01
 */
@Entity
@Table(name = "lcv2_topics_rows")
public class TopicRow extends BaseEntity {
    private Product product;//对应产品
    private int rowId = -1;//行索引
    private Topic topic;//所属专题
    private String image;//图片地址
    private int order = -1;//列索引
    private String link;//图片连接
    private String text;//图片上的字

    /**
     * 获取对应产品
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    /**
     * 设置对应产品
     */
    public void setProduct(Product topicId) {
        this.product = topicId;
    }

    /**
     * 获取行索引
     */
    @Column(name = "row_id")
    public int getRowId() {
        return rowId;
    }

    /**
     * 设置行索引
     */
    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    /**
     * 获取所属专题
     */
    @ManyToOne
    @JoinColumn(name = "topic_id")
    public Topic getTopic() {
        return topic;
    }

    /**
     * 设置所属专题
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * 获取图片地址
     */
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    /**
     * 设置图片地址
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取列索引
     */
    @Column(name = "\"order\"")
    public int getOrder() {
        return order;
    }

    /**
     * 设置列索引
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * 获取图片连接
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置图片连接
     */
    public void setLink(String link) {
        this.link = link;
    }

    public String getText() {
        if (text == null) return "";
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
