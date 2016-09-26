package com.github.kevinjom.servleting.servlet3x;

import com.github.kevinjom.servleting.servlet3x.spring.FilterBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@FilterBean(urlPatterns = "/*", initParams = {@WebInitParam(name = "excludes", value = ".html,.css,.js")})
public class UserFilter implements Filter {
    private List<String> excludedResoruces = new LinkedList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludes = filterConfig.getInitParameter("excludes");
        if (excludes != null && excludes.length() > 0) {
            excludedResoruces = Arrays.asList(excludes.split(",\b*"));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        boolean excluded = excludedResoruces.stream().anyMatch(req.getServletPath()::endsWith);
        if (!excluded) {
            Optional<String> userId = decodeUserId(req.getCookies());
            if (!userId.isPresent()) {
                resp.setStatus(401);
                resp.getWriter().write("invliad user");
                return;
            }
            req.setAttribute("userId", userId.get());

        }

        chain.doFilter(req, resp);
    }

    private Optional<String> decodeUserId(Cookie[] cookies) {
        if (cookies == null) {
            return Optional.empty();
        }

        Optional<Cookie> userCookie = Arrays.asList(cookies).stream()
                .filter(cookie -> "userId".equals(cookie.getName())).findFirst();
        if (userCookie.isPresent() && userCookie.get().getValue().startsWith("k")) {
            return Optional.of("kevin");
        }

        return Optional.of("someone");
    }

    @Override
    public void destroy() {
        System.out.println("filter destorying...");
    }
}
