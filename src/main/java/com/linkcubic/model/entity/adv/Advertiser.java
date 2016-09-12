package com.linkcubic.model.entity.adv;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

/**
 * 内容摘要 ：广告主实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月29日 11:54
 */
@Entity
@Table(name = "lc_advertisers")
public class Advertiser extends BaseEntity {
    private String name;//广告主名称
    private float commission;//是广告主给linkCubic的佣金比例
    private String prefix;//点击转跳目标地址的前缀，由广告联盟提供，通过该前缀访问过去
    private String productUrl;//商品gz包
    private String alliance;//广告联盟
    private float userCommission;//
    private String domain;//
    private String synopsis;//简介
    private String logo;
    private int top;
    private Set<Check> checkSet;

    public Advertiser(Long id) {
        super(id);
    }

    public Advertiser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getAlliance() {
        return alliance;
    }

    public void setAlliance(String alliance) {
        this.alliance = alliance;
    }

    @Column(name = "user_commission")
    public float getUserCommission() {
        return userCommission;
    }

    public void setUserCommission(float userCommission) {
        this.userCommission = userCommission;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Transient
    public Set<Check> getCheckSet() {
        return checkSet;
    }

    public void setCheckSet(Set<Check> checkSet) {
        this.checkSet = checkSet;
    }
}
