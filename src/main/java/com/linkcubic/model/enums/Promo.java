package com.linkcubic.model.enums;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月10日 15:39
 */
public enum Promo implements BaseEnum<String> {
    cash("现金卷"), coupons("折扣卷");

    private String text;

    Promo(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
