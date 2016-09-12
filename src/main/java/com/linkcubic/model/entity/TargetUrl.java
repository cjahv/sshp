package com.linkcubic.model.entity;

import com.linkcubic.model.entity.adv.Advertiser;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月18日 08:29
 */
@Entity
@Table(name = "lcv2_target_url")
public class TargetUrl extends BaseEntity {
    private String longUrl;
    private Advertiser advertiser;

    @Column(name = "long_url")
    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @ManyToOne
    @JoinColumn(name = "aid")
    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }
}
