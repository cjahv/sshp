package com.linkcubic.model.enums.user;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年六月23日 16:49
 */
public enum RankType implements BaseEnum<String> {
    individual("个人"),
    company("公司");

    RankType(String text) {
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

    public void setText(String text) {
        this.text = text;
    }
}
