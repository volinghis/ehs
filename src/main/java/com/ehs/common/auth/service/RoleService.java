package com.ehs.common.auth.service;

import java.util.List;

import com.ehs.common.auth.entity.SysRole;

public interface RoleService {

	public List<SysRole> findRoleBySysUser(String sysUserKey);
	
	public List<SysRole> findRoleByMenuKey(String menuKey);
	
}
