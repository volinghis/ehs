/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.security.entity 
 * @author: chentm   
 * @date: 2019年5月28日 上午10:42:52 
 */
package com.ehs.common.organization.entity.entitysuper;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import com.ehs.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: UserBaseInfo.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 上午10:42:52 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@MappedSuperclass
public abstract  class OrgUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	public static final String DATA_CODE="dataCode";
	public static final String NAME="name";
	public static final String ORG_KEY="orgKey";
	public static final String ORG_NAME="orgName";
	public static final String STATE="state";
	public static final String SYS_USER_KEY="sysUserKey";
	public static final String EMAIL="email";
	public static final String TELEPHONE="telephone";
	public static final String POSITION="position";
	public static final String GENDER="gender";
	public static final String EDUCATION="education";
	public static final String GRADUATED_SCHOOL="graduatedSchool";
	public static final String HOME_TOWN="homeTown";
	public static final String PROFESSION="profession";
	public static final String GRADUATED_DATE="graduatedDate";
	
	public static final String ROLES="roles";
	
	/**
	 * 员工编号
	 */
	private String dataCode;
	
	/**
	 * 所属组织
	 */
	private String orgKey;
	
	/**
	 * 组织名称
	 */
	private String orgName;
	
	/**
	 * 0正常，1锁定
	 */
	private Integer state;
	
	/**
	 * 员工账号
	 */
	private String sysUserKey;
	
	/**
	 * 员工姓名
	 */
	private String name;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 手机
	 */
	private String telephone;
	
	/**
	 * 职务
	 */
	private String position;
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 学历
	 */
	private String education;
	
	/**
	 * 毕业学校
	 */
	private String graduatedSchool;
	
	/**
	 * 籍贯
	 */
	private String homeTown;
	
	/**
	 * 所学专业
	 */
	private String profession;
	
	/**
	 * 毕业时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Timestamp graduatedDate;
	
	/**
	 * 角色
	 */
	@Column(length = 4000)
	private String roles;
	
	public String getOrgKey() {
		return orgKey;
	}

	public void setOrgKey(String orgKey) {
		this.orgKey = orgKey;
	}
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDataCode() {
		return dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getSysUserKey() {
		return sysUserKey;
	}

	public void setSysUserKey(String sysUserKey) {
		this.sysUserKey = sysUserKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGraduatedSchool() {
		return graduatedSchool;
	}

	public void setGraduatedSchool(String graduatedSchool) {
		this.graduatedSchool = graduatedSchool;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public Timestamp getGraduatedDate() {
		return graduatedDate;
	}

	public void setGraduatedDate(Timestamp graduatedDate) {
		this.graduatedDate = graduatedDate;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
}
