/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hbzf.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	public static final int ILLEGAL_ARGUMENT_CODE = 100;
	public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";
	public static final int TIME_OUT_CODE = 110;
	public static final String TIME_OUT_MESSAGE = "安全认证失败，时间过期";
	public static final int SECURE_FAIL_CODE = 120;
	public static final String SECURE_FAIL_MESSAGE = "安全认证失败，签名错误";
	public static final int SUCCESS_CODE = 200;
	public static final String SUCCESS_MESSAGE = "OK";
	public static final int ERROR_CODE = 500;
	public static final String ERROR_MESSAGE = "抱歉，当前值班小哥翘班了，我们马上报告老板！";
	
	public R() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}


	public static R ok(int code, String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}


	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
