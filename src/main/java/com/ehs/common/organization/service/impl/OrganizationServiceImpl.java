package com.ehs.common.organization.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.organization.bean.OrganizationBean;
import com.ehs.common.organization.entity.OrganizationInfo;
import com.ehs.common.organization.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {

//	@Resource
//	private OrganizationDao organizationDao;
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Override
	@Transactional
	public OrganizationInfo saveOrg(OrganizationBean orgBean) {
		// TODO Auto-generated method stub
		OrganizationInfo orgInfo = new OrganizationInfo();
		String key=UUID.randomUUID().toString();
		orgInfo.setKey(key);
		orgInfo.setDataCode(key);
		orgInfo.setName(orgBean.getLabel());
		orgInfo.setParentKey(orgBean.getParentId());
		orgInfo.setSort(Integer.parseInt(orgBean.getId()));
		orgInfo =baseCommonService.saveOrUpdate(orgInfo);
		return orgInfo;
	}
}
