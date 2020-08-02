package com.hbzf.draw.enums;

/**
 * 上传成功/失败枚举
 */
public enum UploadResultEnum {
    SUCCESS(1,"成功"),
    FAILED(0, "失败");
    private int code;
    private String desc;
    UploadResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
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
}
