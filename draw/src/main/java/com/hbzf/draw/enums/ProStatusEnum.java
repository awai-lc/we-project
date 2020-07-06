package com.hbzf.draw.enums;

/**
 * 项目状态
 */
public enum ProStatusEnum {
    /**
     *
     */
    D(1,"底稿");

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    ProStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
