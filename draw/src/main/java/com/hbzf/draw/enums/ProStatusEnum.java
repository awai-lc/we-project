package com.hbzf.draw.enums;

/**
 * 项目状态
 */
public enum ProStatusEnum {
    /**
     *
     */
    D(1,"底稿"),
    DCQ(2,"待抽取"),
    CQZ(3,"抽取中"),
    WC(4,"抽取完成");

    private int code;
    private String desc;

    public static ProStatusEnum parseCode(int code) {
        for (ProStatusEnum value : ProStatusEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

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
