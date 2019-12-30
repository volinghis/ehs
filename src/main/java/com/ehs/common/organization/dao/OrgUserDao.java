package com.ehs.common.organization.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.organization.entity.OrgUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月26日 上午11:21:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月26日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface OrgUserDao extends JpaRepository<OrgUser, String> {

	/**
	 * 
	* @Function: OrgUserDao.java
	* @Description: 根据用户的dataCode或者名称模糊查询用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:21:32 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select u from OrgUser u where u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' and (u."+OrgUser.DATA_CODE+" like %?1% or u."+OrgUser.NAME+" like %?1% ) order by  "+BaseEntity.CREATION_TIME+" desc")
	public Page<OrgUser> findUsers(String query, PageRequest pageRequest);

	/**
	 * 
	* @Function: OrgUserDao.java
	* @Description: 根据查询条件和选择部门交集查询用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:22:31 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select u from OrgUser u where (u."+OrgUser.DATA_CODE+" like %?2% or u."+OrgUser.NAME+" like %?2% ) and u.orgKey=?1 and u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"'" )
	public Page<OrgUser> findUserByOrgKey(String orgKey, String query, PageRequest pageRequest);

	/**
	 * 
	* @Function: OrgUserDao.java
	* @Description: 查询所有用户并分页
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:23:06 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select u from OrgUser u where u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"' order by "+BaseEntity.CREATION_TIME+" desc")
	public Page<OrgUser> findUsers(PageRequest pageRequest);

	/**
	 * 
	* @Function: OrgUserDao.java
	* @Description: 根据部门查找用户
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:23:43 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select u from OrgUser u where u.orgKey=?1 and u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"'" )
	public List<OrgUser> findUsrByOrgKey(String key);

	/**
	 * 
	* @Function: OrgUserDao.java
	* @Description: 根据部门查找用户分页
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 下午2:02:28 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select u from OrgUser u where u.orgKey=?1 and u."+OrgUser.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"'" )
	public Page<OrgUser> findUserByOrgKey(String orgKey, PageRequest pageRequest);

	public OrgUser findOrgUserBySysUserKey(String sysUserKey);
}
