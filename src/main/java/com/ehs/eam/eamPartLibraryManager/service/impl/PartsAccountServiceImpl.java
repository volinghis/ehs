package com.ehs.eam.eamPartLibraryManager.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.oper.bean.PageInfoBean;
import com.ehs.eam.eamPartLibraryManager.bean.QueryBean;
import com.ehs.eam.eamPartLibraryManager.dao.PartsAccountDao;
import com.ehs.eam.eamPartLibraryManager.entity.PartsAccount;
import com.ehs.eam.eamPartLibraryManager.service.PartsAccountService;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryServiceImpl.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午3:58:51 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/

@Service
public class PartsAccountServiceImpl implements PartsAccountService{

	@Resource
	private PartsAccountDao eamPartLibraryDao;
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsAccountService#saveOrUpdateEamPart(com.ehs.eam.eamPartLibraryManager.entity.PartsAccount)  
	* @Function: EamPartLibraryServiceImpl.java
	* @Description: 保存备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午4:09:54 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	@Transactional
	public PartsAccount saveOrUpdateEamPart(PartsAccount eamPartLibrary) {
		// TODO Auto-generated method stub
		try {
			return baseCommonService.saveOrUpdate(eamPartLibrary);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	* @see com.ehs.eam.eamPartLibraryManager.service.PartsAccountService#findEamPartLibraryAll(com.ehs.eam.eamPartLibraryManager.bean.QueryBean)  
	* @Function: EamPartLibraryServiceImpl.java
	* @Description: 查询所有备件
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月30日 下午4:09:59 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月30日     zhaol           v1.0.0               修改原因
	 */
	@Override
	public PageInfoBean findPartsAccountAll(QueryBean queryBean) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(queryBean.getPage()-1, queryBean.getSize());
		Page<PartsAccount> eamParts = eamPartLibraryDao.findEamPart(queryBean.getQuery(), pageRequest);
		if (eamParts!=null) {
			PageInfoBean pb=new PageInfoBean();
			pb.setDataList(eamParts.getContent());
			pb.setTotalCount(eamParts.getTotalElements());
			return pb;
		}
		return null;
	}

}
