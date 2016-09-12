package com.linkcubic.model.entity.promo;

import com.sshp.model.entity.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static com.sshp.model.constant.TombstoneConstant.READ_STATUS_WHERE;

/**
 * 内容摘要 ：优惠活动优惠卷实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月10日 15:48
 */
@Entity
@Table(name = "lcv2_promo_code")
@Where(clause = READ_STATUS_WHERE)
public class Code extends BaseEntity {
    private String code;//优惠卷
    private Activities activity;//所属活动
    private Boolean status;//是否已被兑换

    /**
     * 获取优惠卷
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置优惠卷
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取所属活动
     */
    @ManyToOne
    @JoinColumn(name = "activity")
    public Activities getActivity() {
        return activity;
    }

    /**
     * 设置所属活动
     */
    public void setActivity(Activities activity) {
        this.activity = activity;
    }

    /**
     * 判断是否已被兑换
     */
    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置是否已被兑换
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
