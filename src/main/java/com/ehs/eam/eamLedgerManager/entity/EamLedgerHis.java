/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.entity 
 * @author: qjj   
 * @date: 2019年12月30日 下午3:52:13 
 */
package com.ehs.eam.eamLedgerManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.eamLedgerManager.entity.entitySuper.EamLedger;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamLedgerHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午3:52:13 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_LEDGER_HIS")
public class EamLedgerHis extends EamLedger {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
