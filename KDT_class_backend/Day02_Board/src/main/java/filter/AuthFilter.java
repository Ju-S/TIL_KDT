package filter;

import commons.AuthConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        for (String item : AuthConfig.WHITE_LIST_URI) {
            if (req.getRequestURI().equals(item)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        if (req.getSession() == null || req.getSession().getAttribute("loginId") == null) {
            res.sendRedirect("/");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
