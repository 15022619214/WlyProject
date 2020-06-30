package com.jysc.system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class JdbcTemplateUtils {

  @Autowired
  private JdbcTemplate  jdbcTemplate;
  
  /**
   * 根据科目编号和银行账号获取凭证数据来源
   * @param yhzh 银行账号
   * @param kmbh  科目编号
   * @return 
   */
  public JSONObject getCredentialsOriginByYhzhAndKmbh(String yhzh,String kmbh){
      JSONObject json = new JSONObject();
      //jdbcTemplate.getDataSource().getConnection().
      return json;
  }

 

}
