/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.service.impl 
 * @author: qjj   
 * @date: 2019年12月12日 下午4:30:10 
 */
package com.ehs.common.auth.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ehs.common.auth.dao.LoginDao;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.service.LoginService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月12日 下午4:30:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月12日     qjj           v1.0.0               修改原因
*/
@Service
public class LoginServiceImpl implements LoginService {

	/** 
	* @see com.ehs.common.auth.service.LoginService#findByAccount()  
	*/
	@Resource
	private LoginDao loginDao;
	
	@Override
	public SysUser findByAccount(String account) {
		// TODO Auto-generated method stub
		return loginDao.findByAccount(account);
	}

}
