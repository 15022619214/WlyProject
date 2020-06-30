package com.jysc.base.json;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jysc.base.mvc.IncoMessageSource;
import com.jysc.base.mvc.exception.IncoValidateDomainException;
import com.jysc.base.validation.AsynPreCheckUtil;

public abstract class ResponseJSONHelp {
	
	private static final String _JSON_ATT = "_json_att";
	private static final String CONTENT_TYPE_CONTENT = "application/json;charset=UTF-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String DOT = ".";
	private static Validator validator;
	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 构建字符串类型的响应实体
	 * 
	 * @param jsonObject 要转换的对象
	 * @param filterNames 要排除的属性
	 * @return
	 */
	public static ResponseEntity<String> buildResponseEntity(Object jsonObject,
			String... properties) {
		String content = JsonUtil.toJSONString(jsonObject, properties);
		return buildStringResponseEntity(content);
	}

	/**
	 * 构建字符串类型的响应实体
	 * 
	 * @param content
	 * @return
	 */
	public static ResponseEntity<String> buildStringResponseEntity(
			String content) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(CONTENT_TYPE, CONTENT_TYPE_CONTENT);
		return new ResponseEntity<String>(content, responseHeaders,
				HttpStatus.OK);
	}
	
	public static ResponseEntity<String> buildTextResponseEntity(
			String content) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(CONTENT_TYPE, "text/html;charset=UTF-8");
		return new ResponseEntity<String>(content, responseHeaders,
				HttpStatus.OK);
	}
	
	/**
	 * bean validator 使用标准的Bean校验单个值是否符合某个约束
	 * 
	 * 
	 * @author Administrator
	 * @param objClass model的类
	 * @param property model的属性名
	 * @param value	想要校验的值
	 * @param group 校验组
	 * @param prefix 前缀
	 */
	public static void checkDomainThrowException(Class objClass,String property,Object value, Class[] groups,String prefix) {
		value = AsynPreCheckUtil.preCheck(objClass, property, value, Default.class, prefix);
		
		Set<ConstraintViolation<Object>> set = new HashSet<ConstraintViolation<Object>>();
		for(Class group : groups) {
			set.addAll(validator.validateValue(objClass,property,value,group));
		}
//		Set<ConstraintViolation<Object>> set = validator.validateValue(objClass,property,value,group);
		Map<String, String> map = new HashMap<String, String>();
		for (ConstraintViolation<Object> constraintViolation : set) {
			System.err.println(constraintViolation.getMessage());
		}
		if (StringUtils.isEmpty(prefix)) {
			for (ConstraintViolation<?> failure : set) {
				String name = failure.getPropertyPath().toString();
				String realName = IncoMessageSource.getMessage(name, name);
				int spaceNum = 1;
				if (map.keySet().contains(realName)) {
					spaceNum++;
					for (int i = 0; i < spaceNum; i++) {
						realName = realName.concat(" ");
					}
				}
				map.put(realName, failure.getMessage());
			}
		} else {
			for (ConstraintViolation<?> failure : set) {
				String name = prefix + DOT
						+ failure.getPropertyPath().toString();
				String realName = IncoMessageSource.getMessage(name, name);
				int spaceNum = 1;
				if (map.keySet().contains(realName)) {
					spaceNum++;
					for (int i = 0; i < spaceNum; i++) {
						realName = realName.concat(" ");
					}
				}
				map.put(realName, failure.getMessage());
			}
		}
		if (map.size() > 0) {
			throw new IncoValidateDomainException(map,IncoValidateDomainException.EValidationType.SINGLE);
		}
	}

	/**
	 * bean validator 使用标准的Bean校验实体的值是否符合规则
	 * 
	 * @param validator
	 * @param model
	 */
	public static void checkDomainThrowException(Object model, Class[] groups,String prefix) {
		if (validator == null || model == null) {
			throw new IllegalArgumentException("checkDomain arguments is null");
		}
		Set<ConstraintViolation<Object>> set = new HashSet<ConstraintViolation<Object>>();
		for(Class group : groups) {
			set.addAll(validator.validate(model,group));
		}
//		Set<ConstraintViolation<Object>> set = validator.validate(model,group);
		Map<String, String> map = new HashMap<String, String>();
		for (ConstraintViolation<Object> constraintViolation : set) {
			System.err.println(constraintViolation.getMessage());
		}
		if (StringUtils.isEmpty(prefix)) {
			for (ConstraintViolation<?> failure : set) {
				String name = failure.getPropertyPath().toString();
				String realName = IncoMessageSource.getMessage(name, name);
				int spaceNum = 1;
				if (map.keySet().contains(realName)) {
					spaceNum++;
					for (int i = 0; i < spaceNum; i++) {
						realName = realName.concat(" ");
					}
				}
				map.put(realName, failure.getMessage());
			}
		} else {
			for (ConstraintViolation<?> failure : set) {
				String name = prefix + DOT
						+ failure.getPropertyPath().toString();
				String realName = IncoMessageSource.getMessage(name, name);
				int spaceNum = 1;
				if (map.keySet().contains(realName)) {
					spaceNum++;
					for (int i = 0; i < spaceNum; i++) {
						realName = realName.concat(" ");
					}
				}
				map.put(realName, failure.getMessage());
			}
		}
		if (map.size() > 0) {
			throw new IncoValidateDomainException(map);
		}
	}


	

	

}
