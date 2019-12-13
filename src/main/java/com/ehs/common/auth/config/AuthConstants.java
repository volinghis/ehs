package com.ehs.common.auth.config;

public class AuthConstants {

	/**
	 * 管理员角色key
	 */
	public static final String ADMIN_ROLE_KEY="sysAdminRoleKey";
	/**
	 * 普通人员角色key
	 */
	public static final String NORMAL_ROLE_KEY="normalRoleKey";
	public static final String RESOURE_MENU_KEY="resoureMenuKey";
	public static final int MAX_TIME_OUT=20;
	public static final String SESSION_SYSUSER_KEY="SESSION_SYSUSER_KEY";
	public static final String SESSION_USER_KEY="SESSION_USER_KEY";
	public static final int VALID_OK_CODE=200;
	//未登录
	public static final int VALID_NO_USER_CODE=901;
	//ip错误
	public static final int VALID_IP_ERROR_CODE=902;
	//无权限
	public static final int VALID_NO_AUTH_CODE=903;
	//方法未注册
	public static final int VALID_SERVER_ERROR=904;
	//客户端异常请求
	public static final int VALID_CLIENT_ERROR=905;
	//鉴权异常
	public static final int VALID_EXCEPTION=906;
	public static final String GLOBAL_MENU_KEY=".*";
	
}
