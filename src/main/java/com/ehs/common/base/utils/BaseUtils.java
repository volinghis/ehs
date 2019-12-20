/**   
 * Copyright © 2019 西安东恒鑫源软件开发有限公司版权所有.
 * 
 * 功能描述：
 * @Package: com.ehs.edm.base.utils 
 * @author: chentm   
 * @date: 2019年5月7日 下午2:46:08 
 */
package com.ehs.common.base.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.bytebuddy.asm.Advice.This;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**   
* Copyright: Copyright (c) 2019 西安东恒鑫源软件开发有限公司
* @ClassName: Constans.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: chentm
* @date: 2019年5月7日 下午2:46:08 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2019年5月7日      chentm          v1.0.0               修改原因
*/
public class BaseUtils {

	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}
	
	/**
	 * 
	* @Function: BaseUtils.java
	* @Description: 生产随机数
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: zhaol
	* @date: 2019年12月17日 上午9:27:16 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年12月17日     zhaol           v1.0.0               修改原因
	 */
	public static String getSalt() {
		// 生成一个16位的随机数
		Random random = new Random();
		StringBuilder sBuilder = new StringBuilder(16);
		sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
		int len = sBuilder.length();
		if (len < 16) {
			for (int i = 0; i < 16 - len; i++) {
				sBuilder.append("0");
			}
		}
		// 生成盐
		return sBuilder.toString();
	}
	
	/**
	 * 
	* @Function: Constans.java
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年5月7日 下午6:26:41 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年5月7日     chentm           v1.0.0               修改原因
	 */
	public static Timestamp getNow() {
		return new Timestamp(System.currentTimeMillis());
	}
	/**
	 * 
	* @Function: BaseUtils.java
	* @Description: 加密
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月22日 下午4:27:15 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     chentm           v1.0.0               修改原因
	 */
	public static String encode(String v) {
		final BASE64Encoder encoder = new BASE64Encoder();
		final String text = v;
		byte[] textByte;
		try {
			textByte = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("加密错误");
		}
		final String encodedText = encoder.encode(textByte);
		return encodedText;
	}
	
	/**
	 * 
	* @Function: BaseUtils.java
	* @Description: 解密
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月22日 下午4:30:18 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     chentm           v1.0.0               修改原因
	 */
	public static String decode(String v) {
		final BASE64Decoder  decoder = new BASE64Decoder();
		String str="";
		try {
			str = new String(decoder.decodeBuffer(v), "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException("解密错误");
		} 
		return str;
	}
	/**
	 * 
	* @Function: BaseUtils.java
	* @Description: 获取IP
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: chentm
	* @date: 2019年7月22日 下午4:49:58 
	*
	* Modification History:
	* Date         Author          Version            Description
	*---------------------------------------------------------*
	* 2019年7月22日     chentm           v1.0.0               修改原因
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
}
