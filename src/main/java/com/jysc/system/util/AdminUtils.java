package com.jysc.system.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.jysc.system.model.Role;
import com.jysc.system.model.User;
/**
 * 判断权限时候使用
 * @author Administrator
 *
 */
@Component
public class AdminUtils {

	 @Autowired
	    private  SecurityUtil securityUtil;
	/**
	 * 管理员，管理权限名称修改
	 */
	public static final String AUTHORITY_MANAGE ="管理员";
	
	public static final String  SALESMAN_MANAGE = "销售人员";
	
	public static final String   TREASURER_MANAGE = "财务人员";
	
	public static final String   DEPT_LEADER_AUDIT = "部门领导审批";
	
	   public static final String   HR_AUDIT = "人事审批";
	   
	   public static final String   REPORT_BACK = "销假";
	   
	   public static final String   MODIFY_APPLY = "调整申请";
	   
	   /**
		 * 判断当前用户是有权限
		 * @return
		 */
		public  boolean containsRoleName(String name) {
			boolean flag = false;
			User user = this.securityUtil.getUserinfo() ;
			if (user == null) {
				return flag;
			}
			Set<String> roles = null;
			try {
				roles = AuthorityUtils.authorityListToSet(this.securityUtil.getUserinfo().getAuthorities());
			} catch (Exception e) {
				roles = new HashSet<>();
			}
			
			if (roles.contains(name)) {
				flag = true;
				return flag;
		    }
			return flag;
		}
		 private class Invalid{
	          
	       }
}
