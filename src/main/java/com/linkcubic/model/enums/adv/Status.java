package com.linkcubic.model.enums.adv;

import com.sshp.model.enums.BaseEnum;

/**
 * 内容摘要 ：
 * 创建人　 ：陈佳慧
 * 创建日期 ：16/8/4
 */
public enum Status implements BaseEnum<String> {
    pass("有效", "auditing-pass"),
    check("审核中", "auditing-ing"),
    not("已失效", "auditing-none");

    Status(String txt, String className) {
        this.text = txt;
        this.className = className;
    }

    private String text;
    private String className;

    @Override
    public String getText() {
        return this.text;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String getName() {
        return super.name();
    }
}
