/**   
 * Copyright © 2020 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity 
 * @author: qjj   
 * @date: 2020年1月2日 下午3:17:20 
 */
package com.ehs.eam.eamLedgerManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2020 西安东恒鑫源软件开发有限公司
* @ClassName: EamInspectors.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2020年1月2日 下午3:17:20 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2020年1月2日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_INSPECTORS",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamInspectors extends com.ehs.eam.eamLedgerManager.entity.entitySuper.EamInspectors{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
