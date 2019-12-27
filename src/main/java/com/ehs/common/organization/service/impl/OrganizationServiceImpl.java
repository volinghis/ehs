package com.ehs.common.organization.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.OrgQueryBean;
import com.ehs.common.organization.dao.OrganizationDao;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.common.organization.service.OrganizationService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月19日 下午4:22:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月19日     zhaol           v1.0.0               修改原因
*/
@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Resource
	private OrganizationDao organizationDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	/**
	 * 
	* @see com.ehs.common.organization.service.OrganizationService#saveOrg(com.ehs.common.organization.entity.OrganizationInfo)  
	* @Function: OrganizationServiceImpl.java
	* @Description: 保存部门
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void saveOrg(OrganizationInfo orgInfo) {
		// TODO Auto-generated method stub
		try {
			baseCommonService.saveOrUpdate(orgInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrganizationService#deleteOrgByKey(java.lang.String)  
	* @Function: OrganizationServiceImpl.java
	* @Description: 删除部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月19日 下午4:32:09 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月19日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void deleteOrgByKey(String key) {
		// TODO Auto-generated method stub
		try {
			baseCommonService.deleteByKey(OrganizationInfo.class, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrganizationService#getAllOrgsTable(java.lang.String, com.ehs.common.organization.bean.OrgQueryBean)  
	* @Function: OrganizationServiceImpl.java
	* @Description: 查询部门
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月26日 上午11:14:26 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月26日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getAllOrgsTable(String orgParentKey,OrgQueryBean queryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest =PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		PageInfoBean pb=new PageInfoBean();
		if(StringUtils.isNotBlank(orgParentKey)) {
			Page<OrganizationInfo> orgs=organizationDao.findOrgsByParentKey(orgParentKey,queryBean.getQuery(), pageRequest);
			if (orgs!=null) {
				pb.setDataList(orgs.getContent());
				pb.setTotalCount(orgs.getTotalElements());
				return pb;
			}
			return null;
		}
		Page<OrganizationInfo> orgspPage=organizationDao.findAllOrgs(queryBean.getQuery(), pageRequest);
		if (orgspPage!=null) {
			pb.setDataList(orgspPage.getContent());
			pb.setTotalCount(orgspPage.getTotalElements());
			return pb;
		}
		return null;
	}
}
