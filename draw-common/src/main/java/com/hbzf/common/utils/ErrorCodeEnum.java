package com.hbzf.common.utils;

import org.omg.CORBA.UNKNOWN;
import org.omg.CORBA.portable.UnknownException;

/**
 * @version v1.0
 * @ProjectName: mall
 * @ClassName: ErrorCodeEnum
 * @Description: TODO(一句话描述该类的功能)
 * @Author: liucheng
 * @Date: 2020/6/30 15:28
 */
public enum  ErrorCodeEnum {
    UNKNOWN_EXCEPTION(10000,"未知错误"),
    VALID_EXCEPTION(10001,"参数格式校验失败");

    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
