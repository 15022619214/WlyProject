package com.jysc.base.mvc.exception;

import java.util.HashMap;
import java.util.Map;

public class IncoValidateDomainException extends RuntimeException {

	public static enum EValidationType {
		SINGLE("single"), BATCH("batch");

		private String text;

		private EValidationType(String text) {
			this.text = text;
		}

		public String text() {
			return text;
		}
	};

	private static final long serialVersionUID = 4345504160068164233L;
	private Map<String, String> validateMap = new HashMap<String,String>();
	// 校验类型 single:单个字段校验 batch:多个字段校验
	private EValidationType validationType = EValidationType.BATCH;

	/**
	 * @return the validationType
	 */
	public EValidationType getValidationType() {
		return validationType;
	}

	/**
	 * @param validationType
	 *            the validationType to set
	 */
	public void setValidationType(EValidationType validationType) {
		this.validationType = validationType;
	}
	
	public IncoValidateDomainException() {
		
	}

	public IncoValidateDomainException(Map<String, String> validateMap) {
		super();
		this.validateMap = validateMap;
	}

	public IncoValidateDomainException(Map<String, String> validateMap,
			EValidationType validationType) {
		super();
		this.validateMap = validateMap;
		this.validationType = validationType;
	}

	public Map<String, String> getValidateMap() {
		return validateMap;
	}

}
