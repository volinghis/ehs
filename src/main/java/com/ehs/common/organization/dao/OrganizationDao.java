package com.ehs.common.organization.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.organization.entity.OrganizationInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月20日 上午10:37:41 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月20日     zhaol           v1.0.0               修改原因
*/
@Repository
public interface OrganizationDao extends JpaRepository<OrganizationInfo, String>  {

	/**
	 * 
	* @Function: OrganizationDao.java
	* @Description: 根据parentKey查询所有子部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月20日 上午10:37:50 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月20日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select org from OrganizationInfo org where org.parentKey=?1 and org."+OrganizationInfo.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"'" )
	public Page<OrganizationInfo> findOrgsByParentKey(String parentKey, String query, PageRequest pageRequest);

	/**
	 * 
	* @Function: OrganizationDao.java
	* @Description: 查询所有部门除了总节点（大唐第二发电厂）
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月20日 上午10:38:49 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月20日     zhaol           v1.0.0               修改原因
	 */
	@Query(" select org from OrganizationInfo org where org."+OrganizationInfo.KEY+" <> 'rootOrg' and org."+OrganizationInfo.DATA_MODEL+"<>'"+DataConfig.UNSHOW_DATA_STATE+"'" )
	public Page<OrganizationInfo> findAllOrgs(String query, PageRequest pageRequest);

}
