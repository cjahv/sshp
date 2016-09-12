package com.linkcubic.model.entity.promo;

import com.linkcubic.model.entity.adv.Advertiser;
import com.linkcubic.model.enums.Promo;
import com.sshp.model.entity.BaseEntity;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：优惠活动表
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月10日 15:36
 */
@Entity
@Table(name = "lcv2_promo_activities")
public class Activities extends BaseEntity {
    private Promo type;//活动优惠类型
    private String title;//活动标题
    private double amount;//优惠力度
    private int clicks;//优惠卷价值
    private Advertiser advertiser;//广告主
    private Date createDate;//活动创建时间
    private String description;//描述
    private String image;//活动横幅
    private Integer flow;//兑换量
    private Date validStart;//活动开始时间
    private Date validEnd;//活动结束时间

    /**
     * 获取活动优惠类型
     */
    public Promo getType() {
        return type;
    }

    /**
     * 设置活动优惠类型
     */
    public void setType(Promo type) {
        this.type = type;
    }

    /**
     * 获取活动标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置活动标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取优惠力度
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 设置优惠力度
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * 获取优惠卷价值
     */
    public int getClicks() {
        return clicks;
    }

    /**
     * 设置优惠卷价值
     */
    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    /**
     * 获取广告主
     */
    @ManyToOne
    @JoinColumn(name = "advertiser")
    public Advertiser getAdvertiser() {
        return advertiser;
    }

    /**
     * 设置广告主
     */
    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    /**
     * 获取活动创建时间
     */
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置活动创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取活动横幅
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置活动横幅
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取兑换量
     */
    @Formula("(SELECT COUNT(1) FROM lcv2_exchange_history h where h.activities_id=id)")
    public Integer getFlow() {
        return flow;
    }

    /**
     * 设置兑换量
     */
    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    /**
     * 获取活动开始时间
     */
    @Column(name = "valid_start")
    public Date getValidStart() {
        return validStart;
    }

    /**
     * 设置活动开始时间
     */
    public void setValidStart(Date validStart) {
        this.validStart = validStart;
    }

    /**
     * 获取活动结束时间
     */
    @Column(name = "valid_end")
    public Date getValidEnd() {
        return validEnd;
    }

    /**
     * 设置活动结束时间
     */
    public void setValidEnd(Date validEnd) {
        this.validEnd = validEnd;
    }
}
