/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:06:14 
 */
package com.ehs.eam.eamLedgerManager.service;

import java.util.List;

import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamRequestBean;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamLedgerService.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午4:06:14 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
public interface EamLedgerService {

	/**   
	* @Function:findEamLedgerList 
	* @Description: 该函数的功能描述
	* @param querybean
	* @return
	* @throws：异常描述
	* @version: v1.0.0
	* @author: qjj
	* @date: 2019年12月30日 下午4:07:10 
	*
	* Modification History:
	* Date        Author        Version      Description
	*---------------------------------------------------------*
	* 2019年12月30日     qjj        v1.0.0            修改原因
	*/
	PageInfoBean findEamLedgerList(EamLedgerQueryBean querybean);

	
	public void saveEamLedger(EamRequestBean eamRequestBean);
	
	public List<EamParameters> getEamParametersByKey(String code);
}
