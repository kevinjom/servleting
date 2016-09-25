package com.github.kevinjom.servleting;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Optional<String> userId = decodeUserId(req.getCookies());
        if (!userId.isPresent()) {
            resp.setStatus(401);
            resp.getWriter().write("invliad user");
            return;
        }
        req.setAttribute("userId", userId.get());
        chain.doFilter(req, resp);
    }

    private Optional<String> decodeUserId(Cookie[] cookies) {
        if (cookies == null) {
            return Optional.empty();
        }

        Optional<Cookie> userCookie = Arrays.asList(cookies).stream().filter(cookie -> "userId".equals(cookie.getName())).findFirst();
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
