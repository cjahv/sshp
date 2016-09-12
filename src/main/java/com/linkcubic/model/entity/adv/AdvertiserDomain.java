package com.linkcubic.model.entity.adv;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 内容摘要 ：广告主域名实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月29日 12:55
 */
@Entity
@Table(name = "lc_advertiser_domain")
public class AdvertiserDomain extends BaseEntity {
    private Advertiser advertiser;//广告主实体引用
    private String domain;//广告主域名

    @ManyToOne
    @JoinColumn(name = "advertiser_id")
    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
