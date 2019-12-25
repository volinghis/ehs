/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.common.auth.service 
 * @author: qjj   
 * @date: 2019年12月24日 上午11:28:42 
 */
package com.ehs.common.auth.service;

import com.ehs.common.auth.entity.SysUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月24日 上午11:28:42 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月24日     qjj           v1.0.0               修改原因
*/
public interface UserService {

	public Boolean changePassWord(SysUser sysUser,String newPass);
}
