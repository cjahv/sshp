package com.linkcubic.model.entity.promo;

import com.linkcubic.model.entity.user.User;
import com.linkcubic.model.enums.orders.PayStatus;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/9/6
 */
@Entity
@Table(name = "lcv2_exchange_orders")
public class Orders extends BaseEntity {
    private User user;
    private Integer income;//兑换的钱 (人名币:分)
    private Integer click;
    private PayStatus payStatus;
    private Date update;

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User uid) {
        this.user = uid;
    }

    @Column(name = "income")
    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    @Column(name = "click")
    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    @Column(name = "pay_status")
    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    @Column(name = "`update`")
    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }
}
