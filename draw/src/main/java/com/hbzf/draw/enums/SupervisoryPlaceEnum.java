package com.hbzf.draw.enums;

public enum SupervisoryPlaceEnum {
    /**
     *
     */
    SBJ(1, "省本级")
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

    SupervisoryPlaceEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SupervisoryPlaceEnum parseCode(int code) {
        for (SupervisoryPlaceEnum value : SupervisoryPlaceEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
