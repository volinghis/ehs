package com.ehs.common.auth.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ehs.common.auth.entity.SysMenu;

@Resource
public interface UserMenuDao extends JpaRepository<SysMenu, String>{
	
	@Query(nativeQuery = true,value =" select distinct r from SysMenu r left join SysRoleMenu  srm on srm.menuKey=r.key  left join SysUserRole sur on srm.roleKey=sur.roleKey where srm.menuKey is null or sur.sysUserKey= ?1")
	public List<SysMenu> findMenus(String sysUserKey);
}
