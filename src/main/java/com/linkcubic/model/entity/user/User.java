package com.linkcubic.model.entity.user;

import com.linkcubic.model.entity.BaseEntity;
import com.linkcubic.model.enums.UserLevel;
import com.linkcubic.model.enums.user.Gender;
import com.linkcubic.model.enums.user.RankType;
import com.support.core.exception.SystemException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月15日 15:04
 */
@Entity
@Table(name = "lc_users")
public class User extends BaseEntity {
    private String username;//用户名
    private String password;//返回验证用户密码,无法返回则NULL
    private String phone;//手机号
    private String openid;//微信openid
    private Long weiboUid;//绑定的微博uid
    private String nickname;//用户昵称
    private Gender gender;//性别
    private int level;//用户级别
    private UserLevel auth;//用户级别
    private Date lastLoginTime;//最后登录时间
    //    private AccountType accountType;//用来区分0：支付宝 1：银行卡
//    //    private Long accountId;
//    private AlipayAccount alipayAccount;
//    private BankAccount bankAccount;
    private Account account;
    private Long city;//归属的城市id
    private int devId;//开发者id默认为0
    private String tokenKey;//
    private boolean accountNonExpired = true;//账户是否过期,过期无法验证
    private boolean accountNonLocked = true;//指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    private boolean credentialsNonExpired = true;//指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    private boolean enabled = true;//是否被禁用,禁用的用户不能身份验证
    private Collection<UserAuth> authorities;//权限列表
    private RankType rank;//公司或个人
    private String headPortrait;//头像
    private Integer click;//总点击量
    private Integer consumeClick;//消耗点击量

    public User() {
    }

    public User(Long id) {
        super.setId(id);
    }

    /**
     * 用户名
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     */
    public void setUsername(String username) {
        if (username == null) return;
        if (username.indexOf('<') >= 0) {
            username = StringEscapeUtils.escapeHtml4(username);
        }
        this.username = username;
    }

    /**
     * 返回验证用户密码,无法返回则NULL
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 返回验证用户密码,无法返回则NULL
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null) {
            if (phone.length() == 0) return;
            if (phone.length() != 11 && phone.charAt(0) != '+') {
                throw new SystemException("手机号格式不正确");
            }/* else if (!Pattern.matches("1[3-9]\\d{9}", phone)) {
                throw new SystemException("手机号格式不正确");
            }*/
        }
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Column(name = "weibo_uid")
    public Long getWeiboUid() {
        return weiboUid;
    }

    public void setWeiboUid(Long weiboUid) {
        this.weiboUid = weiboUid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public UserLevel getAuth() {
        return auth;
    }

    public void setAuth(UserLevel auth) {
        this.auth = auth;
    }

    @Column(name = "lastlogin")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @OneToOne
    @JoinColumn(name = "account")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    /**
     * 开发者id默认为0
     */
    public int getDevId() {
        return devId;
    }

    /**
     * 开发者id默认为0
     */
    public void setDevId(int devId) {
        this.devId = devId;
    }

    @Column(name = "token_key")
    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    /**
     * 账户是否过期,过期无法验证
     */
    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    /**
     * 账户是否过期,过期无法验证
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    /**
     * 指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
     */
    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    /**
     * 指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    /**
     * 是否被禁用,禁用的用户不能身份验证
     */
    @Override
    @Column(name = "valid")
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * 是否被禁用,禁用的用户不能身份验证
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 权限列表
     */
    @Override
    @Transient
    public Collection<UserAuth> getAuthorities() {
        return authorities;
    }

    /**
     * 权限列表
     */
    public void setAuthorities(Collection<UserAuth> authorities) {
        this.authorities = authorities;
    }

    public RankType getRank() {
        return rank;
    }

    public void setRank(RankType rank) {
        this.rank = rank;
    }

    @Column(name = "head_portrait")
    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Formula("(select sum(d.click) from lcv2_user_statistic_days d where d.uid=id)")
    public Integer getClick() {
        if (click == null) click = 0;
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    @Column(name = "consume_click")
    public Integer getConsumeClick() {
        return consumeClick;
    }

    public void setConsumeClick(Integer consumeClick) {
        this.consumeClick = consumeClick;
    }
}
