package com.ehs.eam.eamPartLibraryManager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ehs.eam.eamPartLibraryManager.entity.entitySuper.PartsAccount;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: EamPartLibraryHis.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月30日 下午4:01:57 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月30日     zhaol           v1.0.0               修改原因
*/
@Entity
@Table(name = "EAM_PARTS_ACCOUNT_HIS")
public class PartsAccountHis extends PartsAccount {

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

}
