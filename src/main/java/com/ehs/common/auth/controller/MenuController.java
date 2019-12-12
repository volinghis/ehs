package com.ehs.common.auth.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ehs.common.auth.bean.MenuNode;
import com.ehs.common.auth.config.AuthConstants;
import com.ehs.common.auth.entity.entitysuper.SysMenu;
import com.ehs.common.auth.interfaces.RequestAuth;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.utils.JsonUtils;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: MenuController.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: zhaol
* @date: 2019年12月11日 下午2:28:43 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年12月11日     zhaol           v1.0.0               修改原因
*/
@Controller
public class MenuController {
	
	@Resource
	private BaseCommonService baseCommonService;

	/**
	 * 
	* @Function: MenuController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月11日 下午2:28:40 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月11日     zhaol           v1.0.0               修改原因
	 */
	@RequestAuth(menuKeys ={AuthConstants.GLOBAL_MENU_KEY})
	@RequestMapping(value = "/auth/menu/menuDatas")
	@ResponseBody
	public String getMenu(HttpServletRequest request,HttpServletResponse response) {
		List<SysMenu> smList =(List<SysMenu>)baseCommonService.findAll(SysMenu.class);
		if (smList == null || smList.isEmpty()) {
			return "[]";
		}
		if (smList.size()> 1) {
			smList.sort((a, b) -> a.getSort() - b.getSort());
		}
		List<MenuNode> menus = new ArrayList<MenuNode>();
		createMenuNode(menus, smList, null);
		return JsonUtils.toJsonString(menus);
	}
	
	/**
	 * 
	* @Function: MenuController.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月11日 下午2:28:51 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月11日     zhaol           v1.0.0               修改原因
	 */
	private void createMenuNode(List<MenuNode> menuNodes,List<SysMenu> menus,String parentkey) {
		menus.stream().filter(s->StringUtils.equals(s.getParentKey(),parentkey)).forEach(c->{
			MenuNode MenuNode=new MenuNode();
			MenuNode.setKey(c.getKey());
			MenuNode.setCode(c.getKey());
			MenuNode.setLabel(c.getName());
			MenuNode.setPath(c.getUrl());
			MenuNode.setComponent(c.getUrl());
			MenuNode.setIcon(c.getIcon());
			MenuNode.setBusiness(c.getBusiness());
			MenuNode.setLeaf(c.getLeaf());
			List ll=new ArrayList();
			createMenuNode(ll,menus,c.getDataCode());
			if(ll.size()>0) {
				MenuNode.setChildren(ll);
			}else {
				MenuNode.setLeaf(true);
			}
			menuNodes.add(MenuNode);
		});
	}
}
