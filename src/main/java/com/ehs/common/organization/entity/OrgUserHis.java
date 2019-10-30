/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年5月28日 上午10:42:52 
 */
package com.ehs.common.organization.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.common.auth.entity.SysUser;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.organization.entity.entitysuper.OrgUser;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserBaseInfo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 上午10:42:52 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name = "ORG_USER_BASE_INFO_his")
public class OrgUserHis extends OrgUser {

	private static final long serialVersionUID = 1L;
	
	
}
