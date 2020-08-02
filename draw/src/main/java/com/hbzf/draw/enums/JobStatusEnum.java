package com.hbzf.draw.enums;

public enum JobStatusEnum {
    /**
     *
     */
    YES(1,"退休"),
    NO(2,"在职");

    private int code;
    private String desc;

    public static JobStatusEnum parseCode(int code) {
        for (JobStatusEnum value : JobStatusEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public static JobStatusEnum parseDesc(String desc) {
        for (JobStatusEnum value : JobStatusEnum.values()) {
            if (value.getDesc().equals(desc)) {
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

    JobStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
