package com.ehs.common.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.dao.RoleDao;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	
	@Resource
	private RoleDao userRoleDao;
	
	@Override
	public List<SysRole> findRoleBySysUser(String sysUserKey) {
		return userRoleDao.findUserRoles(sysUserKey);
	}

	@Override
	public List<SysRole> findRoleByMenuKey(String menuKey) {
		return userRoleDao.findMenuRoles(menuKey);
	}

	
}
