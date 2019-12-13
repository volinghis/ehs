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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.ehs.common.base.config.RedisCacheConfig;
import com.ehs.common.base.entity.BaseEntity;
import com.ehs.common.base.service.BaseCommonService;
import com.ehs.common.base.service.InitDataService;


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
	
	
	@Resource
	private BaseCommonService baseCommonService;
	
	
	@Resource
	private RedisTemplate redisTemplate;
	
	
	
	@Value("${spring.redis.cache.applicaion.model}")
	private String applicationModel;
	

	
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
		if(StringUtils.equalsIgnoreCase(applicationModel, RedisCacheConfig.CACHE_MODEL_PROD)) {
			logger.info("开始清除缓存数据...,");
			redisTemplate.getConnectionFactory().getConnection().flushAll();
			logger.info("结束清除缓存数据");
		}
		//初始化数据
		initResource();


	}
	
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
	
	private void readElement(List<com.ehs.common.base.entity.BaseEntity> baseEntityList,Element el,String targetClass) throws Exception{
		Iterator<Element> itsIterator = el.elementIterator();
		while (itsIterator.hasNext()) {
			Element element = itsIterator.next();
			if(StringUtils.isNotBlank(targetClass)&&element.attribute("targetClass")==null) {
				String key=element.attribute("key").getText();
				BaseEntity baseEntity = (BaseEntity) Class.forName(targetClass).getConstructor().newInstance();
				
				BaseEntity tempEntity=baseCommonService.findByKey(baseEntity.getClass(), key);
				if(tempEntity==null) {
					Iterator<Attribute> itas = element.attributeIterator();
					while (itas.hasNext()) {
						Attribute attribute = itas.next();
						 List<Field> fieldList = new ArrayList<>() ;
						 Class tempClass =baseEntity.getClass();
						 while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
						       fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
						       tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
						 }
					        for(int i=0;i<fieldList.size();i++){  
					        	Field field=fieldList.get(i);
					        	if((!field.isAnnotationPresent(Transient.class))&&(!Modifier.isStatic(field.getModifiers()))&&!Modifier.isFinal(field.getModifiers())) {
					        		field.setAccessible(true);
					        		if(StringUtils.equals(field.getName(), attribute.getName())) {
					        			if(StringUtils.equals(Boolean.class.getName(), field.getType().getName())) {
					        				field.set(baseEntity, Boolean.valueOf(attribute.getText()));
					        			}else if(StringUtils.equals(Integer.class.getName(), field.getType().getName())){
					        				field.set(baseEntity, Integer.valueOf(attribute.getText()));
					        			}else {
					        				field.set(baseEntity, attribute.getText());
					        			}
					        		}
					        		
					        	}
	
					        }  
	
					}
					baseEntityList.add(baseEntity);
				}
			}
			if(element.attribute("targetClass")!=null) {
				targetClass=element.attributeValue("targetClass").toString();
			}

			readElement(baseEntityList, element, targetClass);
		}
	}
	
}
