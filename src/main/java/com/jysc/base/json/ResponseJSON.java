package com.jysc.base.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jysc.base.mvc.IncoMessageSource;


public class ResponseJSON {
	/**
	 * 单个字段校验还是批量校验
	 */
	private String validationType;
	
	private String repeatSubmitToken;

  private String validateMessagesShowId = "_validatorMessage";

  private String url;

  private boolean status = true;// success or failure

  private int httpstatus = 200;// http status

  private Object data;

  /**
   * 传递的属性集合
   */
  private Map<String, Object> attributes = new HashMap<String, Object>();

  /**
   * 自定义的显示信息，这部分信息会在alert中显示出来
   */
  private List<String> messages = new ArrayList<String>();

  /**
   * 验证信息
   */
  private Map<String, String> validateMessages = new HashMap<String, String>();

  private ResponseJSON() {
  }

  public String getValidateMessagesShowId() {
    return validateMessagesShowId;
  }

  public ResponseJSON changeValidMessageShowId(String validateMessagesShowId) {
    this.validateMessagesShowId = validateMessagesShowId;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public int getHttpstatus() {
    return httpstatus;
  }

  public ResponseJSON setHttpstatus(int httpstatus) {
    this.httpstatus = httpstatus;
    return this;
  }

  public Object getData() {
    return data;
  }

  public ResponseJSON setData(Object data) {
    this.data = data;
    return this;
  }

  

  public List<String> getMessages() {
    return messages;
  }

  public Map<String, String> getValidateMessages() {
    return validateMessages;
  }

  public ResponseJSON addAlertMessage(String message) {
    this.messages.add(message);
    return this;
  }

  public ResponseJSON addValidationMessages(String key, String message) {
    this.validateMessages.put(key, message);
    return this;
  }

  public ResponseJSON addAttribute(String key, Object message) {
    this.attributes.put(key, message);
    return this;
  }
  
  public Object getAttribute(String key) {
	  return this.attributes.get(key);
  }
  
  public Map<String,Object> getAttributeMap() {
	  return this.attributes;
  }

  public static ResponseJSON instance() {
    return new ResponseJSON();
  }

  public ResponseJSON addAlertMessageKey(String... keys) {
    if (keys != null && keys.length > 0) {
      for (String key : keys) {
        String message = IncoMessageSource.getMessage(key, key);
        this.messages.add(message);
      }
    }
    return this;
  }

/**
 * @return the validationType
 */
public String getValidationType() {
	return validationType;
}

/**
 * @param validationType the validationType to set
 */
public void setValidationType(String validationType) {
	this.validationType = validationType;
}

/**
 * @return the repeatSubmitToken
 */
public String getRepeatSubmitToken() {
	return repeatSubmitToken;
}

/**
 * @param repeatSubmitToken the repeatSubmitToken to set
 */
public void setRepeatSubmitToken(String repeatSubmitToken) {
	this.repeatSubmitToken = repeatSubmitToken;
}
  
  
}
