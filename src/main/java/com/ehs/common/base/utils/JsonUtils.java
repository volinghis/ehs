package com.ehs.common.base.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static  ObjectMapper objectMapper = new ObjectMapper();

	public static String toJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object parseObject(String jsonStr, Class targetClass) {
		try {
			return objectMapper.readValue(jsonStr, targetClass);
		} catch (IOException e) {
			throw new RuntimeException("json转换异常");
		}
	}
	
	public static Object parseObject(String jsonStr, TypeReference typeReference) {
		try {
			return objectMapper.readValue(jsonStr, typeReference);
		} catch (IOException e) {
			throw new RuntimeException("json转换异常");
		}
	}
	
	public static List parseList(String jsonStr, Class targetClass){
		try {
			 JavaType jt = JsonUtils.objectMapper.getTypeFactory().constructParametricType(List.class, targetClass);
			 return objectMapper.readValue(jsonStr, jt);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("json转换异常");
		}
	}
}
