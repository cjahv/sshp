package com.linkcubic.model.enums.user;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年四月29日 09:45
 */
public enum Gender implements BaseEnum<String> {
    unknown("其他"), girl("女"), boy("男");

    Gender(String text) {
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
