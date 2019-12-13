package com.ehs.common.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.bean.MenuRolesBean;
import com.ehs.common.auth.dao.MenuRoleDao;
import com.ehs.common.auth.dao.UserMenuDao;
import com.ehs.common.auth.entity.SysMenu;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.entity.SysRoleMenu;
import com.ehs.common.auth.service.MenuService;
import com.ehs.common.base.service.BaseCommonService;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private BaseCommonService baseCommonService;
	
	
	@Resource
	private MenuRoleDao menuRoleDao;
	
	@Resource
	private UserMenuDao userMenuDao;
	
	@Transactional
	@Override
	public void saveMenuRole(MenuRolesBean menuRoleBean) {
		List<SysMenu> menus=(List<SysMenu>)baseCommonService.findAll(SysMenu.class);
		SysMenu sm=baseCommonService.findByKey(SysMenu.class, menuRoleBean.getMenuKey());
		List<SysMenu> childrenMenus=new ArrayList<SysMenu>();
		childrenMenus.add(sm);
		createChildrenMenu(menus, childrenMenus, sm.getKey());
		List<String> menuKeys=  childrenMenus.stream().map(SysMenu::getKey).collect(Collectors.toList());
		List<String> roleKeys=  menuRoleBean.getRoleList().stream().map(SysRole::getKey).collect(Collectors.toList());
		List<SysRoleMenu> srmList= menuRoleDao.find(menuKeys.stream().toArray(String[]::new), roleKeys.stream().toArray(String[]::new));
		
		
		for(SysMenu s: childrenMenus) {
			for(SysRole ss:menuRoleBean.getRoleList()) {
				if(srmList!=null&&!srmList.isEmpty()) {
					long c=srmList.stream().filter(sss->StringUtils.equals(sss.getMenuKey(), s.getKey())&&StringUtils.equals(sss.getRoleKey(), ss.getKey())).count();
					if(c<=0) {
						SysRoleMenu srm=new SysRoleMenu();
						srm.setMenuKey(s.getKey());
						srm.setRoleKey(ss.getKey());
						baseCommonService.saveOrUpdate(srm);
					}
				}else {
					SysRoleMenu srm=new SysRoleMenu();
					srm.setMenuKey(s.getKey());
					srm.setRoleKey(ss.getKey());
					baseCommonService.saveOrUpdate(srm);
				}
			
			}
		}
	}

	private void createChildrenMenu(List<SysMenu> menus,List<SysMenu> childrenMenus,String parentKey) {
		menus.forEach(s->{
			if(StringUtils.equals(s.getParentKey(), parentKey)) {
				childrenMenus.add(s);
				createChildrenMenu(menus, childrenMenus, s.getKey());
			}
		});
	}

	@Transactional
	@Override
	public void deleteMenuRole(MenuRolesBean menuRoleBean) {
		List<SysMenu> menus=(List<SysMenu>)baseCommonService.findAll(SysMenu.class);
		SysMenu sm=baseCommonService.findByKey(SysMenu.class, menuRoleBean.getMenuKey());
		List<SysMenu> childrenMenus=new ArrayList<SysMenu>();
		childrenMenus.add(sm);
		createChildrenMenu(menus, childrenMenus, sm.getKey());
		List<String> menuKeys=  childrenMenus.stream().map(SysMenu::getKey).collect(Collectors.toList());
		List<String> roleKeys=  menuRoleBean.getRoleList().stream().map(SysRole::getKey).collect(Collectors.toList());
		List<SysRoleMenu> srmList= menuRoleDao.find(menuKeys.stream().toArray(String[]::new), roleKeys.stream().toArray(String[]::new));
		if(srmList!=null&&!srmList.isEmpty()) {
			srmList.stream().forEach(s->{
				baseCommonService.deleteByKey(s.getClass(),s.getKey());
			});
		}
	}

	@Override
	public List<SysMenu> findSysMenus(String sysUserKey) {
		return userMenuDao.findMenus(sysUserKey);
	}

}
