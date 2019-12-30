package com.ehs.common.organization.service;

import java.util.List;

import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.bean.UserRolesBean;
import com.ehs.common.organization.entity.OrgUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月26日 上午11:15:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月26日     zhaol           v1.0.0               修改原因
*/
public interface OrgUserService {

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 查询部门下所有用户并分页
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:15:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean getAllUser(UserQueryBean userQueryBean);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 保存用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:16:37 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public OrgUser saveUser(OrgUser orgUser);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 改变用户状态
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:16:48 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public OrgUser changeState(OrgUser orgUser);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 删除用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:17:10 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public void deleteOrgUser(String key);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 分页根据部门查找用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:17:22 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public PageInfoBean findUserByOrgKey(String orgKey,UserQueryBean userQueryBean, UserQueryBean uq);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 查找用户的所有角色
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:17:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public List<SysRole> findRolesByUserKey(String userKey);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 保存用户角色（给用户授予角色）
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:18:07 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public void saveUserRole(UserRolesBean userRolesBean);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 删除用户角色（给用户取消授予的角色）
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:18:33 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public void deleteUserRole(UserRolesBean userRolesBean);

	/**
	 * 
	* @Function: OrgUserService.java
	* @Description: 根据部门查找该部门下所有用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:19:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	public List<OrgUser> findUserByOrgKey(String key);

	public PageInfoBean findAllRolesByUserKey(String userKey, RoleQueryBean queryBean);
	
	/**
	 * 
	* @Function:findOrgUserByDataCode 
	* @Description: 根据dataCode获取当前用户
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月27日 下午3:51:22 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月27日     qjj        v1.0.0            修改原因
	 */
	public OrgUser findOrgUserBySysUserKey(String code);
}
