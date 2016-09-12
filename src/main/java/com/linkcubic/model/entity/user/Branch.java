package com.linkcubic.model.entity.user;

import com.sshp.model.entity.BaseEntity;

import javax.persistence.*;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月23日 17:07
 */
@Entity
@Table(name = "lc_bank_branchs")
public class Branch extends BaseEntity {
    private Bank bank;
    private City city;
    private String name;
    private String address;
    private String telphone;

    @ManyToOne
    @JoinColumn(name = "bank")
    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @ManyToOne
    @JoinColumn(name = "city")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "telphone")
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
