package com.ehs.common.auth.service;

import com.ehs.common.auth.bean.MenuRolesBean;
import com.ehs.common.auth.bean.RoleBean;

public interface MenuService {

	public void saveMenuRole(MenuRolesBean menuRolesBean);
	
	public void deleteMenuRole(MenuRolesBean menuRolesBean);
	
}
