package com.ehs.common.organization.service;

import com.ehs.common.organization.bean.OrganizationBean;
import com.ehs.common.organization.entity.OrganizationInfo;

public interface OrganizationService {
	
	public OrganizationInfo saveOrg(OrganizationBean orgBean);
}
