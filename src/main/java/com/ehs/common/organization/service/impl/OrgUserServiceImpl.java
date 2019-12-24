package com.ehs.common.organization.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.bean.RoleBean;
import com.ehs.common.auth.entity.SysRole;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.entity.entitysuper.SysMenu;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.base.utils.JsonUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.UserBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.dao.OrgUserDao;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.service.OrgUserService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrgUserServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月20日 下午6:08:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月20日     zhaol           v1.0.0               修改原因
*/
@Service
public class OrgUserServiceImpl implements OrgUserService{

	@Resource
	private OrgUserDao orgUserDao;
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#getAllUser(com.ehs.common.organization.bean.UserQueryBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:29 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean getAllUser(UserQueryBean userQueryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest =PageRequest.of(userQueryBean.getPage()-1, userQueryBean.getSize());
		if (StringUtils.isNotBlank(userQueryBean.getQuery())) {
			Page<OrgUser> users=orgUserDao.findUsers(userQueryBean.getQuery(), pageRequest);
			if (users!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(users.getContent());
				pb.setTotalCount(users.getTotalElements());
				return pb;
			}
			return null;
		}
		else {
			Page<OrgUser> users=orgUserDao.findUsers(pageRequest);
			if (users!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(users.getContent());
				pb.setTotalCount(users.getTotalElements());
				return pb;
			}
			return null;
		}
	}
	
	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#findUserByOrgKey(java.lang.String, com.ehs.common.organization.bean.UserQueryBean, com.ehs.common.organization.bean.UserQueryBean)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:35 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findUserByOrgKey(String orgKey,UserQueryBean userQueryBean,UserQueryBean uq) {
		// TODO Auto-generated method stub
		PageRequest pageRequest =PageRequest.of(userQueryBean.getPage()-1, userQueryBean.getSize());
		if (StringUtils.isNotBlank(orgKey)) {
			Page<OrgUser> users=orgUserDao.findUserByOrgKey(orgKey,userQueryBean.getQuery(), pageRequest);
			System.out.println("根据部门查询");
			if (users!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(users.getContent());
				pb.setTotalCount(users.getTotalElements()); 
				return pb;
			}
			return null;
		}else {
			System.out.println("查询所有用户");
			return this.getAllUser(uq);
		}
	}
	
	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#saveUser(com.ehs.common.organization.entity.OrgUser)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public OrgUser saveUser(OrgUser orgUser) {
		// TODO Auto-generated method stub
		String salt = BaseUtils.getSalt();
		SysUser sysUser= new SysUser();
		orgUser.setSysUserKey(orgUser.getDataCode());
		String dataCode = orgUser.getDataCode();
		sysUser.setAccount(dataCode);
		sysUser.setState(0);
		sysUser.setVersionId(0l);
		sysUser.setKey(UUID.randomUUID().toString());
		sysUser.setAttribute1(salt);
		sysUser.setPassword(BaseUtils.string2MD5(sysUser.getAccount()+"123456"+salt));
		baseCommonService.saveOrUpdate(sysUser);
		//保存用户信息
		orgUser.setSysUserKey(sysUser.getKey());
		orgUser.setState(sysUser.getState());
		orgUser.setAttribute1(String.valueOf(sysUser.getState()));
		OrgUser oUser =baseCommonService.saveOrUpdate(orgUser);
		return oUser;
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#changeState(com.ehs.common.organization.entity.OrgUser)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:45 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public OrgUser changeState(OrgUser orgUser) {
		// TODO Auto-generated method stub
		SysUser sysUser = baseCommonService.findByKey(SysUser.class, orgUser.getSysUserKey());
		orgUser.setState(orgUser.getState()== 0 ? 0: 1);
		sysUser.setState(orgUser.getState()== 0 ? 0: 1);
		baseCommonService.saveOrUpdate(sysUser);
		return baseCommonService.saveOrUpdate(orgUser);
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#deleteOrgUser(java.lang.String)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:52 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public void deleteOrgUser(String key) {
		// TODO Auto-generated method stub
		try {
			OrgUser orgUser = baseCommonService.findByKey(OrgUser.class, key);
			baseCommonService.deleteByKey(OrgUser.class, key);
			baseCommonService.deleteByKey(SysUser.class, orgUser.getSysUserKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @see com.ehs.common.organization.service.OrgUserService#findRolesByUserKey(java.lang.String)  
	* @Function: OrgUserServiceImpl.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月24日 上午9:23:56 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月24日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public List<SysRole> findRolesByUserKey(String userKey) {
		return null;
		// TODO Auto-generated method stub
//		OrgUser orgUser = baseCommonService.findByKey(OrgUser.class, userKey);
//		System.out.println("user--name============"+orgUser.getName());
//		List<SysRole> roles = new ArrayList<SysRole>();
//		if (orgUser != null) {
//			List<UserBean> userBeans =JsonUtils.parseList(orgUser.getRoles(),UserBean.class);
//			for (UserBean userBean : userBeans) {
//				roles.add(baseCommonService.findByKey(SysRole.class, userBean.getRoleKey()));
//			}
//		}
//		return roles;
	}

}
