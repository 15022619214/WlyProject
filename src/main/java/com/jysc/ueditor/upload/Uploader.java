package com.jysc.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jysc.ueditor.define.State;

public class Uploader {
    private final Logger logger = LoggerFactory.getLogger(getClass());
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(HttpServletRequest request, Map<String, Object> conf) {
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
        logger.info("filedName:"+filedName);
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request,this.request.getParameter(filedName),this.conf);
            logger.info("stateBase64Uploader:"+state);
		} else {
			state = BinaryUploader.save(this.request, this.conf);
            logger.info("stateBinaryUploader:"+state);
		}

		return state;
	}
}
