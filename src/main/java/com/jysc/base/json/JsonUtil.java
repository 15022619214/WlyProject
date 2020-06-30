package com.jysc.base.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class JsonUtil {
	

	public static String toJSONString(Object object,String ... properties) {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
		for(String property : properties) {
			filter.getExcludes().add(property);
		}
		return JSON.toJSONString(object,filter,SerializerFeature.PrettyFormat,SerializerFeature.DisableCircularReferenceDetect);
	}

}
