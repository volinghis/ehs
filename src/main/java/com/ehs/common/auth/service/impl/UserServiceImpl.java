/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.service.impl 
 * @author: qjj   
 * @date: 2019年12月24日 上午11:31:10 
 */
package com.ehs.common.auth.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ehs.common.auth.bean.PassWordBean;
import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.auth.service.UserService;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.BaseUtils;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月24日 上午11:31:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月24日     qjj           v1.0.0               修改原因
*/
@Service
public class UserServiceImpl implements UserService {

	
	@Resource
	private BaseCommonService baseCommonService;
	
	/** 
	* @see com.ehs.common.auth.service.UserService#changePassWord(com.ehs.common.auth.bean.PassWordBean)  
	*/
	@Transactional
	@Override
	public Boolean changePassWord(SysUser sysUser,String newPass) {
		// TODO Auto-generated method stub
		newPass=BaseUtils.string2MD5(sysUser.getAccount()+newPass+sysUser.getSalt());
		sysUser.setPassword(newPass);
		BaseEntity bEntity=	baseCommonService.saveOrUpdate(sysUser);
		return bEntity!=null? true: false;
	}

}
