package com.linkcubic.model.entity.article;

import com.linkcubic.model.entity.user.User;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月19日 10:59
 */
@Entity(name = "articleFavorite")
@Table(name = "lcv2_user_favorite_articles")
public class Favorite extends BaseEntity {
    private User user;
    private Article article;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "article")
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
