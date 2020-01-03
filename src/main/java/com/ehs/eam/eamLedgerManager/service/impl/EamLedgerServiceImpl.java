/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.eam.eamLedgerManager.service.impl 
 * @author: qjj   
 * @date: 2019年12月30日 下午4:06:57 
 */
package com.ehs.eam.eamLedgerManager.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean;
import com.ehs.eam.eamLedgerManager.bean.EamRequestBean;
import com.ehs.eam.eamLedgerManager.dao.EamLedgerDao;
import com.ehs.eam.eamLedgerManager.dao.EamParametorsDao;
import com.ehs.eam.eamLedgerManager.entity.EamInspectors;
import com.ehs.eam.eamLedgerManager.entity.EamLedger;
import com.ehs.eam.eamLedgerManager.entity.EamParameters;
import com.ehs.eam.eamLedgerManager.service.EamLedgerService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamLedgerServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: qjj
* @date: 2019年12月30日 下午4:06:57 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     qjj           v1.0.0               修改原因
*/
@Service
public class EamLedgerServiceImpl implements EamLedgerService {
	
	@Resource
	private BaseCommonService baseCommonService;
	
	@Resource
	private EamLedgerDao eamLedgerDao;
	
	@Resource
	private EamParametorsDao eamParametersDao;

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#findEamLedgerList(com.ehs.eam.eamLedgerManager.bean.EamLedgerQueryBean)  
	*/
	@Override
	public PageInfoBean findEamLedgerList(EamLedgerQueryBean querybean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(querybean.getPage() - 1, querybean.getSize());
		Page<EamLedger> eamLedgers = eamLedgerDao.findEamLedgerList(querybean.getQuery(), pageRequest);
		if (eamLedgers != null) {
			PageInfoBean pb = new PageInfoBean();
			pb.setDataList(eamLedgers.getContent());
			pb.setTotalCount(eamLedgers.getTotalElements());
			return pb;
		}
		return null;
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#saveEamLedger(com.ehs.eam.eamLedgerManager.bean.EamRequestBean)  
	*/
	@Transactional
	@Override
	public void saveEamLedger(EamRequestBean eamRequestBean) {
		// TODO Auto-generated method stub
		String eamLedgerKey=eamRequestBean.getEamLedger().getKey();
		baseCommonService.saveOrUpdate(eamRequestBean.getEamLedger());
		
		List<EamParameters> parameters= eamRequestBean.getParamsList();
		if (parameters!=null&& parameters.size()>0) {
			
			for (EamParameters ep : parameters) {
				ep.setDeviceKey(eamLedgerKey);
				baseCommonService.saveOrUpdate(ep);
			}
		}
		
		List<EamInspectors> inspectorsList=eamRequestBean.getInspectorsList();
		if (!CollectionUtils.isEmpty(inspectorsList)) {
			
			for (EamInspectors ei : inspectorsList) {
				ei.setDevicekey(eamLedgerKey);
				baseCommonService.saveOrUpdate(ei);
			}
		}
	}

	/** 
	* @see com.ehs.eam.eamLedgerManager.service.EamLedgerService#getEamParametersByKey(java.lang.String)  
	*/
	@Override
	public List<EamParameters> getEamParametersByKey(String code) {
		// TODO Auto-generated method stub
		return eamParametersDao.findEamParametersByDeviceKey(code);
	}


}
