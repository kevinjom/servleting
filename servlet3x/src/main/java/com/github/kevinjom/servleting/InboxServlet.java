package com.github.kevinjom.servleting;

import com.github.kevinjom.servleting.spring.ApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/inbox/*")
public class InboxServlet extends HttpServlet {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATOIN_JSON = "applicatoin/json";

    private InboxService inboxService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        inboxService = ApplicationContextUtils.getBean(this.getServletContext(), InboxService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader(CONTENT_TYPE, APPLICATOIN_JSON);
        resp.setStatus(200);

        String userId = (String) req.getAttribute("userId");

        resp.getWriter().append(ObjectMapperFactory.get()
                .writeValueAsString(inboxService.getAll(userId)));
    }
}

