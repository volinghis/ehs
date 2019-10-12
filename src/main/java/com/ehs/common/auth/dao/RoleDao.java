package com.ehs.common.auth.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;

@Repository
public interface RoleDao extends JpaRepository<SysRole, String>{

	@Query(" select r from SysRole r,SysUserRole sur where sur.roleKey=r.key and sur.sysUserKey=?1 and  sur."+BaseEntity.VERSION_ID+" = "+DataConfig.VERSION_EFFECTIVE +" and r."+BaseEntity.VERSION_ID+" = "+DataConfig.VERSION_EFFECTIVE )
	public List<SysRole>  findUserRoles(String sysUserKey);
	
	
	@Query(" select r from SysRole r,SysRoleMenu srm where srm.roleKey=r.key and srm.menuKey=?1 and  srm."+BaseEntity.VERSION_ID+" = "+DataConfig.VERSION_EFFECTIVE +" and r."+BaseEntity.VERSION_ID+" = "+DataConfig.VERSION_EFFECTIVE )
	public List<SysRole>  findMenuRoles(String menuKey);
}
