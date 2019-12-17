package com.ehs.common.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.bean.MenuRolesBean;
import com.ehs.common.auth.bean.RoleBean;
import com.ehs.common.auth.entity.SysMenu;
import com.ehs.common.auth.service.MenuService;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private BaseCommonService baseCommonService;

	@Transactional
	@Override
	public void saveMenuRole(MenuRolesBean menuRolesBean) {
		List<SysMenu> menus = (List<SysMenu>) baseCommonService.findAll(SysMenu.class);
		SysMenu sm = baseCommonService.findByKey(SysMenu.class, menuRolesBean.getMenuKey());
		List<SysMenu> childrenMenus = new ArrayList<SysMenu>();
		childrenMenus.add(sm);
		createChildrenMenu(menus, childrenMenus, sm.getKey());
		for (SysMenu s : childrenMenus) {
			List<RoleBean> roleBeans = JsonUtils.parseList(s.getRoles(), RoleBean.class);
			roleBeans.addAll(menuRolesBean.getRoleList());
			s.setRoles(JsonUtils.toJsonString(roleBeans));
			baseCommonService.saveOrUpdate(s);
		}
	}

	private void createChildrenMenu(List<SysMenu> menus, List<SysMenu> childrenMenus, String parentKey) {
		menus.forEach(s -> {
			if (StringUtils.equals(s.getParentKey(), parentKey)) {
				childrenMenus.add(s);
				createChildrenMenu(menus, childrenMenus, s.getKey());
			}
		});
	}

	@Transactional
	@Override
	public void deleteMenuRole(MenuRolesBean menuRolesBean) {
		List<SysMenu> menus = (List<SysMenu>) baseCommonService.findAll(SysMenu.class);
		SysMenu sm = baseCommonService.findByKey(SysMenu.class, menuRolesBean.getMenuKey());
		List<SysMenu> childrenMenus = new ArrayList<SysMenu>();
		childrenMenus.add(sm);
		createChildrenMenu(menus, childrenMenus, sm.getKey());
		List<RoleBean> tempList = menuRolesBean.getRoleList();
		for (SysMenu sysMenu : childrenMenus) {
			List<RoleBean> roleBeans = JsonUtils.parseList(sysMenu.getRoles(), RoleBean.class);
			if (roleBeans.containsAll(tempList)) {
				roleBeans.removeAll(tempList);
				sysMenu.setRoles(JsonUtils.toJsonString(roleBeans));
				baseCommonService.saveOrUpdate(sysMenu);
			}
		}
	}

}
