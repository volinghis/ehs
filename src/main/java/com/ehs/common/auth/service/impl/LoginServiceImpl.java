/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.login.service.impl 
 * @author: qjj   
 * @date: 2019年12月11日 上午10:31:32 
 */
package com.ehs.common.auth.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.service.LoginService;
import com.ehs.common.base.dao.BaseCommonDao;
import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月11日 上午10:31:32 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月11日     qjj           v1.0.0               修改原因
*/
@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private BaseCommonDao baseCommonDao;

	/** 
	* @see com.ehs.common.auth.service.LoginService#login(java.lang.String)  
	*/
	@Override
	public SysUser login(String account) {
		// TODO Auto-generated method stub
		StringBuilder builder=new StringBuilder(" select su from SysUser su  ");
		builder.append(" where su.");
		builder.append(BaseEntity.VERSION_ID);
		builder.append(" =?0 and  su.");
		builder.append(SysUser.ACCOUNT);
		builder.append(" =?1 ");
		List params=new LinkedList();
		params.add(0, 0l);
		params.add(1, account);
		List<BaseEntity> baseEntities=  baseCommonDao.find(builder.toString(), params);
		if(baseEntities==null||baseEntities.isEmpty()) {
			return null;
		}
		return (SysUser)baseEntities.get(0);
	}

}
