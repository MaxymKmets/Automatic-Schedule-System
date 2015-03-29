package com.util.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by root on 24.03.15.
 */
public class UserSecuritySuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_USER")){
            httpServletResponse.sendRedirect("/user/details");
            return;
        }
        if (roles.contains("ROLE_PLANNER")){
            httpServletResponse.sendRedirect("/planner/details");
            return;
        }
        if (roles.contains("ROLE_MANAGER")){
            httpServletResponse.sendRedirect("/manager/details");
            return;
        }
        httpServletResponse.sendRedirect("");
    }
}
