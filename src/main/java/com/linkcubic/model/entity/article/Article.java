package com.linkcubic.model.entity.article;

import com.linkcubic.model.entity.user.User;
import com.linkcubic.model.enums.Audit;
import com.sshp.model.entity.BaseEntity;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：文章实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月05日 16:09
 */
@Entity
@Table(name = "lc_articles")
public class Article extends BaseEntity {
    private String title;//文章标题
    private String body;//文章内容
    private Date created;//文章创建时间
    private User author;//用户id
    private String face;//缩略图
    private Audit audited;//审核结果
    private Category category;//分类引用
    private String categoryName;//分类名称
    private int sort;//推荐位排序
    private String currentUrl;//当前文章所发布的完整URL地址
    private String abstracts;//简介
    private boolean favorite;//当前用户是否收藏
    private Long collectAmount;//收藏量
    private Boolean net;//是否属于网络文章
    private String url;//网络文章源连接
    private Long weiboUid;//微博文章的UID
    private String shortParam;//文章短连接参数

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @ManyToOne
    @JoinColumn(name = "author")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Transient
    public String getNickname() {
        if (author == null) return null;
        return author.getNickname();
    }

    public void setNickname(String nickname) {
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Audit getAudited() {
        return audited;
    }

    public void setAudited(Audit audited) {
        this.audited = audited;
    }

    @ManyToOne
    @JoinColumn(name = "categoryid")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Column(name = "current_url")
    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    @Transient
    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Formula("(SELECT COUNT(ufa.article) FROM lcv2_user_favorite_articles ufa where id=ufa.article)")
    public Long getCollectAmount() {
        return collectAmount;
    }

    public void setCollectAmount(Long collectAmount) {
        this.collectAmount = collectAmount;
    }

    public Boolean getNet() {
        if (net == null) return false;
        return net;
    }

    public void setNet(Boolean net) {
        this.net = net;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "weibo_uid")
    public Long getWeiboUid() {
        return weiboUid;
    }

    public void setWeiboUid(Long weiboUid) {
        this.weiboUid = weiboUid;
    }

    @Column(name = "short_param")
    public String getShortParam() {
        return shortParam;
    }

    public void setShortParam(String shortParam) {
        this.shortParam = shortParam;
    }
}
