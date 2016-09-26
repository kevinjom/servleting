package com.github.kevinjom.servleting.servlet3x.spring;

import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;

public final class ApplicationContextUtils {

    public static final String ROOT_IOC_CONTAINER = "ROOT_IOC_CONTAINER";

    public static ApplicationContext getApplicationContext(ServletContext servletContext) {
        return (ApplicationContext) servletContext.getAttribute(ROOT_IOC_CONTAINER);
    }


    public static <T> T getBean(ServletContext servletContext, Class<T> type) {
        return getApplicationContext(servletContext).getBean(type);
    }
}
