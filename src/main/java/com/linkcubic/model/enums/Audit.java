package com.linkcubic.model.enums;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：文章审核枚举
 * 创建人　 ：陈佳慧
 * 创建日期 ：2016年五月05日 16:37
 */
public enum Audit implements BaseEnum<String> {
    Unapproved("未审核"),
    Passed("已通过"),
    DidNotPass("未通过"),
    draft("草稿");

    private String text;

    Audit(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public String getName() {
        return super.name();
    }
}
