package com.ehs.common.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.bean.MenuRolesBean;
import com.ehs.common.auth.bean.RoleBean;
import com.ehs.common.auth.bean.RoleQueryBean;
import com.ehs.common.auth.dao.RoleDao;
import com.ehs.common.auth.entity.SysMenu;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.service.MenuService;
import com.ehs.common.auth.service.RoleService;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private BaseCommonService baseCommonService;

	@Resource
	private RoleService roleService;

	@Resource
	private RoleDao rolesDao;

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

	/** 
	* @see com.ehs.common.auth.service.MenuService#findLeftRolesByMenuKey(com.ehs.common.auth.bean.RoleQueryBean)  
	*/
	@Override
	public PageInfoBean findLeftRolesByMenuKey(RoleQueryBean queryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(queryBean.getPage() - 1, queryBean.getSize());
		Page<SysRole> allRoles = rolesDao.findRoles(queryBean.getQuery(), pageRequest);
		
		List<SysRole> resultList=new ArrayList<SysRole>();
		//当前菜单已有角色
		List<SysRole> roles=roleService.findRolesByMenuKey(queryBean.getMenuKey());
		if(roles==null||roles.isEmpty()) {
		  resultList=allRoles.getContent().stream().filter(
					s->(!StringUtils.equals(s.getKey(), "sysAdminRoleKey"))
					).collect(Collectors.toList());
		}
		 resultList=allRoles.getContent().stream().filter(
				s->roles.stream().allMatch(ss->(!StringUtils.equals(s.getKey(), ss.getKey()))
				&&(!StringUtils.equals(s.getKey(), "sysAdminRoleKey")))
				).collect(Collectors.toList());
	
		 if(resultList==null||resultList.isEmpty()) {
				return null;
		}
		PageInfoBean pb = new PageInfoBean();
		pb.setDataList(resultList);
		pb.setTotalCount(resultList.size());
		return pb;
	}
}
