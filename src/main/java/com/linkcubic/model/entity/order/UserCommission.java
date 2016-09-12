package com.linkcubic.model.entity.order;

import com.linkcubic.model.entity.user.User;
import com.linkcubic.model.enums.orders.PayStatus;
import com.linkcubic.model.enums.orders.Status;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：交易->用户实体
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月18日 11:07
 */
@Entity
@Table(name = "lcv2_user_orders")
public class UserCommission extends BaseEntity {
    private User user;
    private int advId;
    private Commission commission;
    private double tmpIncome;
    private double tmpIncomeCny;
    private float exchangeRate;//汇率
    private Status status;
    private int rank;
    private Date eventDate;
    private PayStatus payStatus;
    private Date update;

    /**
     * 用户id
     */
    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    /**
     * 用户id
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 广告主
     */
    @Column(name = "adv_id")
    public int getAdvId() {
        return advId;
    }

    /**
     * 广告主
     */
    public void setAdvId(int advId) {
        this.advId = advId;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    @Column(name = "tmp_income")
    public double getTmpIncome() {
        return tmpIncome;
    }

    public void setTmpIncome(double tmpIncome) {
        this.tmpIncome = tmpIncome;
    }

    @Column(name = "tmp_income_cny")
    public double getTmpIncomeCny() {
        return tmpIncomeCny;
    }

    public void setTmpIncomeCny(double tmpIncomeCny) {
        this.tmpIncomeCny = tmpIncomeCny;
    }

    /**
     * 汇率
     */
    @Column(name = "xrate")
    public float getExchangeRate() {
        return exchangeRate;
    }

    /**
     * 汇率
     */
    public void setExchangeRate(float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "rank")
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Column(name = "event_date")
    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Column(name = "pay_status")
    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    @Column(name = "update")
    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }
}
