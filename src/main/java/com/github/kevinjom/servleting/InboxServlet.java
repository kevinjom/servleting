package com.github.kevinjom.servleting;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InboxServlet extends HttpServlet {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATOIN_JSON = "applicatoin/json";

    private final ObjectMapper objectMapper;
    private final InboxRepository inboxRepository;

    public InboxServlet() {
        objectMapper = new ObjectMapper();
        inboxRepository = new InboxRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader(CONTENT_TYPE, APPLICATOIN_JSON);
        resp.setStatus(200);

        String userId = (String) req.getAttribute("userId");

        resp.getWriter().append(objectMapper.writeValueAsString(inboxRepository.getAll(userId)));
    }
}

