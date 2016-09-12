package com.linkcubic.model.entity.user;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/7/15
 */
@Entity
@Table(name = "lcv2_login_log")
public class LoginLog extends BaseEntity {
    private User user;
    private Date loginTime;

    public LoginLog(User user, Date loginTime) {
        this.user = user;
        this.loginTime = loginTime;
    }

    public LoginLog() {
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "login_time")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
