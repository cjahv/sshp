package com.linkcubic.model.enums.product;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/23
 */
public enum UrlType implements BaseEnum<String> {
    New("", 0),
    Index("", 1),
    Price("", 2),
    Sale("", 3);

    UrlType(String text, int flag) {
        this.text = text;
        this.flag = flag;
    }

    private String text;
    private int flag;

    @Override
    public String getText() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    public int getFlag() {
        return flag;
    }
}
