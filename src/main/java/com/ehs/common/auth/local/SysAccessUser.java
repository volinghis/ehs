/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.login.util 
 * @author: chentm   
 * @date: 2019年5月28日 下午5:02:04 
 */
package com.ehs.common.auth.local;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.ehs.common.auth.local.bean.LocalUser;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: SysAccessUser.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 下午5:02:04 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
public class SysAccessUser {
	private static final  ThreadLocal<LocalUser> threadlocal=new TransmittableThreadLocal<LocalUser>();
	
//	/*
//	 * 赋值
//	 */
//	
	public static void set(LocalUser userBean) {
		threadlocal.set(userBean);
	}
//	
//	/*
//	 * 取值
//	 */
	public static LocalUser get() {
		return (LocalUser) threadlocal.get();
	}
	/*
	 * 移除
	 */
	public static void remove() {
		threadlocal.remove();
	}
}
