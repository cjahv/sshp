package com.linkcubic.model.entity.user;

import com.linkcubic.model.enums.user.AccountType;
import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月23日 16:54
 */
@Entity
@Table(name = "lcv2_account")
public class Account extends BaseEntity {
    private String account;
    private User user;
    private AccountType type;
    private String name;
    private Branch branch;

    public Account() {
    }

    public Account(Long id) {
        super(id);
    }

    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @ManyToOne
    @JoinColumn(name = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User uid) {
        this.user = uid;
    }

    @Column(name = "type")
    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne
    @JoinColumn(name = "branchs")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
