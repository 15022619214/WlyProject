package com.jysc.system.security;

import java.util.Collection;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

@Configuration
public class SecurityAuthorityVoter implements AccessDecisionVoter<Object> {
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
       return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {
        FilterInvocation fi = (FilterInvocation) o;
        String url = fi.getRequestUrl();
        String name = authentication.getName();
        System.out.println( name + ":" + url);
        // 不需要登录也能访问的url
        if (url.equals("/")
                || url.startsWith("/admin")
                || url.startsWith("/login")
                || url.startsWith("/403")

                || url.startsWith("/api/getIsOpenPlots")
                || url.startsWith("/api/getAllplotsisopen")
                || url.startsWith("/api/getplotsinfoByplotsid")
                || url.startsWith("/plotsinfo")
                || url.startsWith("/api/getServices")
                || url.startsWith("/api/getServicesType")
                || url.startsWith("/api/getBottomBtn")
                )  {
            return 1;
        }
        return 0;
    }
}
