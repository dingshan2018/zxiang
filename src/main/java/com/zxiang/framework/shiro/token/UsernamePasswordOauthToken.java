package com.zxiang.framework.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordOauthToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String openId;

	public UsernamePasswordOauthToken() {
		super();
	}
	
	public UsernamePasswordOauthToken(String username,String password,Boolean rememberMe,String openId) {
		super(username,password,rememberMe);
		this.openId = openId;
	}
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	

}
