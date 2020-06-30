package com.jysc.system.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
/*
 * 
 * 配置登录后根据角色进入不同的页面（方法）
 * */
public class LoginSuccessHandle implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException,ServletException {

			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			String path = request.getContextPath() ;
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			if(roles == null || roles.size() == 0) {
				response.sendRedirect(basePath+"403");
				return;
			}else if(roles.contains("管理员")){
                response.sendRedirect(basePath+"plotsadmin");
                return;
            }else if(roles.contains("分享")){
                response.sendRedirect(basePath+"plotsshare");
                return;
            }else if(roles.contains("普通用户")){
                response.sendRedirect(basePath+"plotsadmin");
                return;
            }
			response.sendRedirect(basePath+"");
    }
}