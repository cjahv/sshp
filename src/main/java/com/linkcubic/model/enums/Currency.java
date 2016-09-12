package com.linkcubic.model.enums;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：商品货币种类
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月28日 12:33
 */
public enum Currency implements BaseEnum<String> {
    USD("美元", "&#36;"),
    CNY("人民币", "&#165;"),
    EUR("欧元", "&#8364;"),
    GBP("英镑", "&#163;"),
    SGD("新加坡元","S&#36;");

    private String text;
    private String currency;

    Currency(String text, String currency) {
        this.text = text;
        this.currency = currency;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return name();
    }

    public String getCurrency() {
        return currency;
    }
}
