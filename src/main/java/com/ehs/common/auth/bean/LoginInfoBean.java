package com.ehs.common.auth.bean;

public class LoginInfoBean {

	private String account;
	
	private String password;
	
	private String username;
	
	private String captchCode;

	
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the captchCode
	 */
	public String getCaptchCode() {
		return captchCode;
	}

	/**
	 * @param captchCode the captchCode to set
	 */
	public void setCaptchCode(String captchCode) {
		this.captchCode = captchCode;
	}
	
	
}
