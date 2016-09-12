package com.linkcubic.model.entity.adv;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：广告主佣金历史记录
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月03日 14:57
 */
@Entity
@Table(name = "lcv2_advertiser_commission_history")
public class AdvertiserCommissionHistory extends BaseEntity {
    private Long advId;//广告主id
    private Date activeDate;//生效时间
    private float commission;//佣金比例

    public AdvertiserCommissionHistory(Long advId, Date activeDate, float commission) {
        this.advId = advId;
        this.activeDate = activeDate;
        this.commission = commission;
    }

    public AdvertiserCommissionHistory() {

    }

    @Column(name = "adv_id")
    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    @Column(name = "active_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public float getCommission() {
        return commission;
    }

    public void setCommission(float commission) {
        this.commission = commission;
    }
}
