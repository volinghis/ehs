/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.dao 
 * @author: qjj   
 * @date: 2019年12月11日 下午3:59:39 
 */
package com.ehs.common.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ehs.common.auth.entity.SysUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginDao.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月11日 下午3:59:39 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月11日     qjj           v1.0.0               修改原因
*/
@Repository
public interface LoginDao  extends JpaRepository<SysUser, String> {
	
	public  SysUser findByAccount(String account);
}
