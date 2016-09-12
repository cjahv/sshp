package com.linkcubic.model.entity.product;

import com.linkcubic.model.entity.user.User;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月19日 15:55
 */
@Entity(name = "productFavorite")
@Table(name = "lcv2_user_favorite_products")
public class Favorite extends BaseEntity {
    private User user;
    private Product product;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User uid) {
        this.user = uid;
    }

    @ManyToOne
    @JoinColumn(name = "product")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
