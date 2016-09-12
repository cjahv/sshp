package com.linkcubic.model.enums.user.notice;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年七月01日 11:28
 */
public enum NoticeType implements BaseEnum<String> {
    system("ion-email", "bg-aqua"),
    warning("ion-alert-circled", "bg-red");

    NoticeType(String text, String color) {
        this.text = text;
        this.color = color;
    }

    private String text;
    private String color;

    @Override
    public String getText() {
        return this.text;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String getName() {
        return name();
    }
}
