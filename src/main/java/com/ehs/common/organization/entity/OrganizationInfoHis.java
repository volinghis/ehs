/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年5月28日 上午10:52:10 
 */
package com.ehs.common.organization.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.organization.entity.entitysuper.OrganizationInfo;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: OrganizationInfo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 上午10:52:10 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name = "ORGANIZATION_INFO_his")
public class OrganizationInfoHis extends OrganizationInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
