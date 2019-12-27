package com.ehs.common.auth.service;

import com.ehs.common.auth.bean.MenuRolesBean;
import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.oper.bean.PageInfoBean;

public interface MenuService {

	public void saveMenuRole(MenuRolesBean menuRolesBean);
	
	public void deleteMenuRole(MenuRolesBean menuRolesBean);
	
	/**
	 * 
	* @Function:findLeftRolesByMenuKey 
	* @Description: 查找不属于当前菜单的所有角色
	* @param queryBean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月27日 下午1:50:47 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月27日     qjj        v1.0.0            修改原因
	 */
	public PageInfoBean findLeftRolesByMenuKey(RoleQueryBean queryBean);
	
}
