package com.jysc.system.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.jysc.system.model.User;
import com.jysc.system.util.Constants;
import com.jysc.system.util.SecurityUtil;

@Component
public class AddUserinfoTosessionFilter extends GenericFilterBean {

    @Autowired
    SecurityUtil securityUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute(Constants.SESSION_CURRENT_USER_KEY) == null){
            User user = securityUtil.getUserinfo();
            if (user != null) {
                user.getRoles().size();
                session.setAttribute(Constants.SESSION_CURRENT_USER_KEY, user);

            }
        }
        filterChain.doFilter(request, response);
    }
  private class Invalid{
        
    }
}
