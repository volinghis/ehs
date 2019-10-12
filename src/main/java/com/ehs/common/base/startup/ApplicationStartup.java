package com.ehs.common.base.startup;
/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.utils 
 * @author: chentm   
 * @date: 2019年5月28日 上午10:07:27 
 */

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.startup.service.InitDataService;


/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: ApplicationStartup.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月28日 上午10:07:27 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月28日      chentm          v1.0.0               修改原因
*/
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger logger=LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Resource
	private InitDataService initDataService;
	
	
	private void initResource() {
		try {
			List<BaseEntity> baseEntityList=new ArrayList<BaseEntity>();
			SAXReader reader = new SAXReader();
			//加载到了初始化资源文件
			InputStream is=this.getClass().getResourceAsStream("/init/initResource.xml");
			if(is!=null) {
				Document document = reader.read(is);
				// 获取根节点
				Element root = document.getRootElement();
				readElement(baseEntityList,root,"");

				logger.info(baseEntityList.size()+" 数据被读取 ");
				initDataService.initData(baseEntityList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	* @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)  
	* @Function: ApplicationStartup.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月28日 上午10:07:47 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月28日      chentm           v1.0.0               修改原因
	*/
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//初始化数据
		initResource();

	}
	
	private void readElement(List<com.ehs.common.base.entity.BaseEntity> baseEntityList,Element el,String targetClass) throws Exception{
		Iterator<Element> itsIterator = el.elementIterator();
		while (itsIterator.hasNext()) {
			Element element = itsIterator.next();
			if(StringUtils.isNotBlank(targetClass)&&element.attribute("targetClass")==null) {
				BaseEntity baseEntity = (BaseEntity) Class.forName(targetClass).getConstructor().newInstance();
				Iterator<Attribute> itas = element.attributeIterator();
				while (itas.hasNext()) {
					Attribute attribute = itas.next();
						// 得到类中的所有属性集合
					Method[] methods = baseEntity.getClass().getMethods();
					for(Method oMethod:methods) {
						if(oMethod.getName().equalsIgnoreCase("set"+attribute.getName())) {
							if(StringUtils.equals(oMethod.getParameterTypes()[0].getTypeName(), Integer.class.getName())) {
								oMethod.invoke(baseEntity, Integer.parseInt(attribute.getText()));
							}else {
								oMethod.invoke(baseEntity, attribute.getText());
							}
								
						}
					}
				}
				baseEntityList.add(baseEntity);
			}
			if(element.attribute("targetClass")!=null) {
				targetClass=element.attributeValue("targetClass").toString();
			}

			readElement(baseEntityList, element, targetClass);
		}
	}
	
}
