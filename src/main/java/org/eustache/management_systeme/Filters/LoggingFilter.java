package org.eustache.management_systeme.Filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("Request URL: " + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse); // Continue the filter chain
    }
}
