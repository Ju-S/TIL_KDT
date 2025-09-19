package com.kedu.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginValidator implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginId = (String) request.getSession().getAttribute("loginId");
        if (loginId == null) {
            response.sendRedirect("/error");
            return false;
        }

        return true;
    }
}
