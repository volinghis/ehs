/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.base.platform.login.config 
 * @author: chentm   
 * @date: 2019年5月16日 上午9:53:26 
 */
package  com.ehs.common.auth.config;

import javax.annotation.Resource;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ehs.common.auth.filter.AuthFilter;



/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: FilterRegister.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月16日 上午9:53:26 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月16日      chentm          v1.0.0               修改原因
*/
@Deprecated
@Configuration
public class FilterRegister {
//
//	@Resource
//	private Environment environment;
//
//	@Bean
//    public FilterRegistrationBean setFilter(){
//
//        FilterRegistrationBean filterBean = new FilterRegistrationBean();
////        filterBean.addInitParameter("exclusions", environment.getProperty("com.ehs.auth.authFilter.ignorePattens"));
////        filterBean.setFilter(new AuthFilter());
////        filterBean.setName("authFilter");
////        filterBean.addUrlPatterns("/*");
////        filterBean.setOrder(1);
//        return filterBean;
//    }
	

}
