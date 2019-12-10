package com.ehs.common.auth.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.auth.entity.entitysuper.SysLoginLog;
import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;



/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: LoginLogEntity.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年6月18日 下午4:24:42 
*
* Modification History:
* Date          Author          Version            Description
*---------------------------------------------------------*
* 2019年6月18日    qjj           v1.0.0               修改原因
*/
@Entity
@Table(name="HIS_SYS_LOGIN_LOG")
public class SysLoginLogHis extends SysLoginLog  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	
}
