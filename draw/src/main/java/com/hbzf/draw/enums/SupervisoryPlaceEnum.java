package com.hbzf.draw.enums;

public enum SupervisoryPlaceEnum {
    /**
     *
     */
    SBJ(1, "省本级"),
    WH(2, "武汉市"),
    HS(3, "黄石市"),
    SY(4, "十堰市"),
    YC(5, "宜昌市"),
    XF(6, "襄樊市"),
    EZ(7, "鄂州市"),
    JM(8, "荆门市"),
    XG(9, "孝感市"),
    JZ(10, "荆州市"),
    HG(11, "黄冈市"),
    SZ(12, "随州市"),
    XT(13, "仙桃市"),
    QJ(14, "潜江市"),
    TM(15, "天门市"),
    SNJ(16, "神农架林区"),
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
