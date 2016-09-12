package com.linkcubic.model.entity.order;

import com.linkcubic.model.entity.adv.Advertiser;
import com.linkcubic.model.enums.orders.Commit;
import com.linkcubic.model.enums.orders.Status;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：交易实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月18日 10:39
 */
@Entity
@Table(name = "lcv2_orders")
public class Commission extends BaseEntity {
    private String sid;
    private Advertiser advertiser;
    private String orderId;
    private Status status;
    private Date eventDate;
    private Commit commitStatus;
    private String originalActionId;
    private String advName;
    private String actionType;
    private float commissionAmount;
    private float saleAmount;

    @Column(name = "sid")
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @ManyToOne
    @JoinColumn(name = "adv_id")
    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    @Column(name = "order_id_o")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "event_date")
    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Column(name = "commit_status")
    public Commit getCommitStatus() {
        return commitStatus;
    }

    public void setCommitStatus(Commit commitStatus) {
        this.commitStatus = commitStatus;
    }

    @Column(name = "original_action_id")
    public String getOriginalActionId() {
        return originalActionId;
    }

    public void setOriginalActionId(String originalActionId) {
        this.originalActionId = originalActionId;
    }

    @Column(name = "adv_name")
    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    @Column(name = "action_type")
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Column(name = "commission_amount")
    public float getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(float commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    @Column(name = "sale_amount")
    public float getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(float saleAmount) {
        this.saleAmount = saleAmount;
    }
}
