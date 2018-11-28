package com.ytoxl.yipin.web.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken{
	/**	 */
	private static final long serialVersionUID = -6665058924125412941L;
	public CustomUsernamePasswordAuthenticationToken(Object principal,
			Object credentials) {
		super(principal, credentials);
		// TODO Auto-generated constructor stub
	}

	//是否第三方登录
	private boolean  isOpenIdOathLogin = false;

	public boolean isOpenIdOathLogin() {
		return isOpenIdOathLogin;
	}

	public void setOpenIdOathLogin(boolean isOpenIdOathLogin) {
		this.isOpenIdOathLogin = isOpenIdOathLogin;
	}
}
