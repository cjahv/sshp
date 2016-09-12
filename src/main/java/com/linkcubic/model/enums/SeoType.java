package com.linkcubic.model.enums;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/23
 */
public enum SeoType implements BaseEnum<String> {
    like("包含"),
    start("从头包含"),
    end("末尾包含"),
    regex("正则表达式");

    SeoType(String text) {
        this.text = text;
    }

    private String text;

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getName() {
        return super.name();
    }
}
