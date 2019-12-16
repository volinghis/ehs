package com.ehs.common.auth.service;

import java.util.List;

import com.ehs.common.auth.bean.MenuRolesBean;
import com.ehs.common.auth.entity.SysMenu;

public interface MenuService {

	public void saveMenuRole(MenuRolesBean menuRoleBean);
	
	public void deleteMenuRole(MenuRolesBean menuRoleBean);
	
	public List<SysMenu> findSysMenus(String userKey);
}
