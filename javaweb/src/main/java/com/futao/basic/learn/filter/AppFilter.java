package com.futao.basic.learn.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author futao
 * @date 2020/4/2.
 */
public class AppFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AppFilter.init");
        System.out.println("InitParameter" + filterConfig.getInitParameter("filterParam"));
        System.out.println("FilterName" + filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        System.out.println("---" + req.getContentType());
        System.out.println(req.getCharacterEncoding());
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json; charset=utf-8");
        HttpSession session = req.getSession(true);
        if (session.isNew()) {
            System.out.println("创建新 new session created");
        }
        //过期时间为10s
        session.setMaxInactiveInterval(10);
        System.out.println("AppFilter.doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("AppFilter.destroy");
    }
}
