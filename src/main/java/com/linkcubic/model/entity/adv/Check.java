package com.linkcubic.model.entity.adv;

import com.linkcubic.model.entity.user.User;
import com.linkcubic.model.enums.adv.Status;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/4
 */
@Entity
@Table(name = "lcv2_advertiser_check")
public class Check extends BaseEntity {
    private Advertiser advertiser;
    private User user;
    private Date start;
    private Date end;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "adv_id")
    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "start_time")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Column(name = "end_time")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
