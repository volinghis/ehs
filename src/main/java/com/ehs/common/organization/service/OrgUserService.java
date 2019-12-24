package com.ehs.common.organization.service;

import java.util.List;

import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.entity.OrgUser;

public interface OrgUserService {

	public PageInfoBean getAllUser(UserQueryBean userQueryBean);

	public OrgUser saveUser(OrgUser orgUser);

	public OrgUser changeState(OrgUser orgUser);

	public void deleteOrgUser(String key);

	public PageInfoBean findUserByOrgKey(String orgKey,UserQueryBean userQueryBean, UserQueryBean uq);

	public List<SysRole> findRolesByUserKey(String userKey);
}
