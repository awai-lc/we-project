package com.hbzf.draw.enums;

public enum PurWayEnum {
    /**
     *
     */
    GKZB(1, "公开招标"),
    JZXTP(2, "竞争性谈判"),
    JZXCS(3, "竞争性磋商"),
    XJCG(4, "询价采购"),
    QT(5, "其他"),
    ;
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

    PurWayEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PurWayEnum parseCode(int code) {
        for (PurWayEnum value : PurWayEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
