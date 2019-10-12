package com.ehs.common.base.utils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.entity.OrganizationInfo;

public class AccessUtils {
	public static String getUserNameByUserKey(String userKey) {
		BaseCommonService baseCommonService=SpringUtils.getBean(BaseCommonService.class);
		OrgUser orgUser=baseCommonService.findByKey(OrgUser.class, userKey);
		if(orgUser!=null) {
			return orgUser.getName();
		}else {
			return "";
		}
	
	}
	public static String getOrgNameByOrgKey(String orgKey) {
		BaseCommonService baseCommonService=SpringUtils.getBean(BaseCommonService.class);
		OrganizationInfo org=baseCommonService.findByKey(OrganizationInfo.class, orgKey);
		if(org!=null) {
			return org.getName();
		}else {
			return "";
		}
	}
}
