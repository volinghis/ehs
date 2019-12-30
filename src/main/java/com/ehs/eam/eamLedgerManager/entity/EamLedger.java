/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedger.entity 
 * @author: qjj   
 * @date: 2019年12月30日 下午3:25:55 
 */
package com.ehs.eam.eamLedgerManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ehs.common.base.config.DataConfig;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Ledger.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午3:25:55 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_LEDGER",uniqueConstraints = @UniqueConstraint(columnNames = DataConfig.TABLE_UNIQUE_KEY))
public class EamLedger extends com.ehs.eam.eamLedgerManager.entity.entitySuper.EamLedger {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
