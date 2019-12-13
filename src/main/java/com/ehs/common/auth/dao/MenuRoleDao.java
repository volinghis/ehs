package com.ehs.common.auth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.auth.entity.SysRoleMenu;

@Repository
public interface MenuRoleDao extends JpaRepository<SysRoleMenu, String>{

	@Query(" select r from SysRoleMenu r  where r."+SysRoleMenu.MENU_KEY+" in ?1 and r."+SysRoleMenu.ROLE_KEY+"  in ?2 ")
	public List<SysRoleMenu> find(String[] menuKey,String[] roleKey);
	
}
