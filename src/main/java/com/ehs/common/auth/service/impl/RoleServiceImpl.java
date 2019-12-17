/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.gsp.auth.role.service.impl 
 * @author: qjj   
 * @date: 2019年10月9日 上午10:11:13 
 */
package com.ehs.common.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.bean.RoleBean;
import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.dao.RoleDao;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.entity.entitysuper.SysMenu;
import com.ehs.common.auth.service.RoleService;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.fasterxml.jackson.databind.JavaType;

/**
 * Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
 * 
 * @ClassName: RoleServiceImpl.java
 * @Description: 该类的功能描述
 *
 * @version: v1.0.0
 * @author: qjj
 * @date: 2019年10月9日 上午10:11:13
 *
 *        Modification History: Date Author Version Description
 *        ---------------------------------------------------------* 2019年10月9日
 *        qjj v1.0.0 修改原因
 */
@Service
public class RoleServiceImpl implements RoleService {

	/**
	 * @see com.ehs.RoleService.auth.role.service.RolesService#findRoles(com.ehs.gsp.auth.role.controller.bean.RoleQueryBean)
	 */
	@Resource
	private RoleDao rolesDao;

	@Resource
	private BaseCommonService baseCommonService;

	@Override
	public PageInfoBean findRoles(RoleQueryBean queryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize());
		Page<SysRole> roles = rolesDao.findRoles(queryBean.getQuery(), pageRequest);
		if (roles != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(roles.getContent());
			pb.setTotalCount(roles.getTotalElements());
			return pb;
		}
		return null;
	}

	/**
	 * @see com.ehs.common.auth.service.RoleService#findRolesByMenuKey(java.lang.String)
	 */
	@Override
	public List<SysRole> findRolesByMenuKey(String menuKey) {
		// TODO Auto-generated method stub
		SysMenu sysMenu = baseCommonService.findByKey(SysMenu.class, menuKey);
		List<SysRole> roles = new ArrayList<SysRole>();
		if (sysMenu != null) {
			List<RoleBean> roleBeans =JsonUtils.parseList(sysMenu.getRoles(),RoleBean.class);
			for (RoleBean roleBean : roleBeans) {
				roles.add(baseCommonService.findByKey(SysRole.class, roleBean.getRoleKey()));
			}
		}
		return roles;
	}

}
