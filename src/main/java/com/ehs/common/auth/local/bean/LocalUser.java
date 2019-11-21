package com.ehs.common.auth.local.bean;

import org.apache.commons.lang.StringUtils;

import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.SpringUtils;
import com.ehs.common.organization.entity.OrgUser;

public class LocalUser {

	private String sysUserKey;
	private String userKey;
	private String orgKey;
	

	public  LocalUser initBySysUser(String sysUserKey) {
		SysUser su=(SysUser)SpringUtils.getBean(BaseCommonService.class).findByKey(SysUser.class, sysUserKey);
		this.setSysUserKey(su.getKey());
		String refUser=su.getUserKey();
		if(StringUtils.isNotBlank(refUser)) {
			OrgUser uu= (OrgUser)SpringUtils.getBean(BaseCommonService.class).findByKey(OrgUser.class, refUser);
			this.setOrgKey(uu.getOrgKey());
			this.setUserKey(su.getUserKey());
		}

		return this;
	}



	public String getOrgKey() {
		return orgKey;
	}



	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}



	public String getSysUserKey() {
		return sysUserKey;
	}



	public void setSysUserKey(String sysUserKey) {
		this.sysUserKey = sysUserKey;
	}



	public String getUserKey() {
		return userKey;
	}



	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
   
}
