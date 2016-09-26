package com.github.kevinjom.servleting.servlet3x;

import com.github.kevinjom.servleting.servlet3x.spring.ServletBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ServletBean(urlPatterns = "/inbox/*")
public class InboxServlet extends HttpServlet {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATOIN_JSON = "applicatoin/json";

    private final InboxService inboxService;

    @Autowired
    public InboxServlet(InboxService inboxService) {
        this.inboxService = inboxService;
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

