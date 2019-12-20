package com.ehs.common.organization.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.common.organization.bean.UserQueryBean;
import com.ehs.common.organization.dao.OrgUserDao;
import com.ehs.common.organization.entity.OrgUser;
import com.ehs.common.organization.service.OrgUserService;

@Service
public class OrgUserServiceImpl implements OrgUserService{

	@Resource
	private OrgUserDao orgUserDao;
	
	@Resource
	private BaseCommonService baseCommonService;

	@Override
	public PageInfoBean getAllUser(UserQueryBean userQueryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest =PageRequest.of(userQueryBean.getPage()-1, userQueryBean.getSize());
		Page<OrgUser> users=orgUserDao.findUsers(userQueryBean.getQuery(), pageRequest);
		System.out.println("findAll----users========"+users.getSize());
		if (users!=null) {
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(users.getContent());
			pb.setTotalCount(users.getTotalElements());
			return pb;
		}
		return null;
	}
	
	@Override
	public PageInfoBean findUserByOrgKey(String orgKey,UserQueryBean userQueryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest =PageRequest.of(userQueryBean.getPage()-1, userQueryBean.getSize());
		if (StringUtils.isNotBlank(orgKey)) {
			Page<OrgUser> users=orgUserDao.findUserByOrgKey(orgKey,userQueryBean.getQuery(), pageRequest);
			System.out.println("user==========="+users);
			if (users!=null) {
				PageInfoBean pb=new PageInfoBean();
				pb.setDataList(users.getContent());
				pb.setTotalCount(users.getTotalElements());
				return pb;
			}
		}
		return this.getAllUser(userQueryBean);
	}
	
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

}
