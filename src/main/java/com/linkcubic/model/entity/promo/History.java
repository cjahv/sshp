package com.linkcubic.model.entity.promo;

import com.linkcubic.model.entity.user.User;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年七月04日 13:41
 */
@Entity
@Table(name = "lcv2_exchange_history")
public class History extends BaseEntity {
    private User user;
    private Integer click;
    private Code code;
    private Date createDate;
    private Activities activities;

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User uid) {
        this.user = uid;
    }

    @Basic
    @Column(name = "click")
    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    @OneToOne
    @JoinColumn(name = "code_id")
    public Code getCode() {
        return code;
    }

    public void setCode(Code codeId) {
        this.code = codeId;
    }

    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @ManyToOne
    @JoinColumn(name = "activities_id")
    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }
}
