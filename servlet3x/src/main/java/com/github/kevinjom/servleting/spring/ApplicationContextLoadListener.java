package com.github.kevinjom.servleting.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ServletingConfiguration.class);
        sce.getServletContext().setAttribute(ApplicationContextUtils.ROOT_IOC_CONTAINER, context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(ApplicationContextUtils.ROOT_IOC_CONTAINER);
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) ApplicationContextUtils.getApplicationContext(sce.getServletContext());
        applicationContext.close();
    }
}
