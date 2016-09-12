package com.linkcubic.model.enums.user;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月19日 09:55
 */
public enum AccountType implements BaseEnum<String> {
    Alipay("支付宝"),
    Bank("银行卡");

    AccountType(String text) {
        this.text = text;
    }

    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getName() {
        return name();
    }
}
