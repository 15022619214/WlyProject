package com.jysc.base.validation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.jysc.base.mvc.IncoMessageSource;
import com.jysc.base.mvc.exception.IncoValidateDomainException;


public class AsynPreCheckUtil {

	public static Object preCheck(Class objClass, String property,
			Object value, Class group, String prefix) {
		String strValue = (String) value;
		Object result = null;
		Class c = BeanUtils
				.findPropertyType(property, new Class[] { objClass });
		if (c == Integer.class) {
			result = checkInteger(property, strValue, group, prefix);
		} else {
			//默认处理String
			result = strValue;
		}
		return result;
	}

	private static Integer checkInteger(String property, String value,
			Class group, String prefix) {
		Integer r = null;
		String realName = IncoMessageSource.getMessage(prefix + "." + property,
				property);
		if (value != null) {
			try {
				r = Integer.parseInt(value);
			} catch (Exception e) {
				Map m = new HashMap();
				m.put(realName, "必须是整数");
				throw new IncoValidateDomainException(m,
						IncoValidateDomainException.EValidationType.SINGLE);
			}
		}
		return r;
	}

}
