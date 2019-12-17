/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.gsp.auth.role.service 
 * @author: qjj   
 * @date: 2019年10月9日 上午10:10:39 
 */
package com.ehs.common.auth.service;

import java.util.List;

import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.oper.bean.PageInfoBean;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: RoleService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年10月9日 上午10:10:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年10月9日     qjj           v1.0.0               修改原因
*/
public interface RoleService {

	public PageInfoBean findRoles(RoleQueryBean queryBean);
	
	/**
	 * 
	* @Function:findRolesByMenuKey 
	* @Description: 根据菜单key获取关联角色
	* @param menuKey
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月16日 下午2:45:29 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月16日     qjj        v1.0.0            修改原因
	 */
	public List<SysRole> findRolesByMenuKey(String menuKey);
}
