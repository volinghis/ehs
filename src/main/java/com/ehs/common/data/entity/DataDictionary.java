/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年6月26日 上午8:43:14 
 */
package com.ehs.common.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;
import com.ehs.common.base.entity.BaseEntity;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: DataDictionary.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年6月26日 上午8:43:14 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年6月26日      chentm          v1.0.0               修改原因
*/
@Entity
@Table(name="DATA_DICTIONARY",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class DataDictionary extends com.ehs.common.data.entity.entitysuper.DataDictionary{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




}
