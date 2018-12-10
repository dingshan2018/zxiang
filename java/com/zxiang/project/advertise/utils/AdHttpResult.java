package com.zxiang.project.advertise.utils;

import java.util.HashMap;
import java.util.Map;

public class AdHttpResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private String code;
	private String msg;

	public AdHttpResult() {
		this.put("code", "0000");
	}

	public AdHttpResult(String code, String msg) {
		this.code = code;
		this.msg = msg;
		this.put("code", code);
		this.put("msg", msg);
	}

	public static AdHttpResult ok() {
		return new AdHttpResult();
	}

	public static AdHttpResult ok(String msg) {
		return new AdHttpResult("0000", msg);
	}

	public static AdHttpResult ok(Map<String, Object> map) {
		AdHttpResult result = new AdHttpResult();
		result.putAll(map);
		return result;
	}

	public static AdHttpResult error() {
		return error("9999", "未知异常，请联系管理员");
	}

	public static AdHttpResult error(String msg) {
		return error("9999", "操作失败，" + msg);
	}

	public static AdHttpResult error(String code, String msg) {
		return new AdHttpResult(code, msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public AdHttpResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}

}
