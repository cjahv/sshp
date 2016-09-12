package com.linkcubic.model.enums;


public enum UserLevel {
    普通用户("ROLE_USER"),
    开发者("ROLE_DEVELOPER"),
    管理员("ROLE_ADMIN"),
    匿名("ROLE_ANONYMOUS");

    private String text;

    UserLevel(String text) {
        this.text = text;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }


}
