package com.ehs.common.auth.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: User.java
* @Description: 用户实体类
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年5月13日 下午3:57:42 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月13日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "SYS_USER",uniqueConstraints = @UniqueConstraint(columnNames = {DataConfig.TABLE_UNIQUE_KEY,com.ehs.common.auth.entity.entitysuper.SysUser.ACCOUNT}))
public class SysUser extends com.ehs.common.auth.entity.entitysuper.SysUser {

	private static final long serialVersionUID = 1L;
	
}
