package com.github.kevinjom.servleting.servlet3x.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;

@WebListener
public class ApplicationContextLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ServletingConfiguration.class);
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute(ApplicationContextUtils.ROOT_IOC_CONTAINER, context);

        addServlets(context, servletContext);

        addFilters(context, servletContext);
    }

    private void addFilters(ApplicationContext context, ServletContext servletContext) {
        Map<String, Filter> filters = context.getBeansOfType(Filter.class);
        filters.forEach((name, filter) -> {
            FilterRegistration.Dynamic registration = servletContext.addFilter(name, filter);
            System.out.println(filter.getClass());
            System.out.println(filter.getClass().getAnnotations().length);
            FilterBean filterBean = filter.getClass().getAnnotationsByType(FilterBean.class)[0];
            registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, filterBean.urlPatterns());
            Arrays.asList(filterBean.initParams()).forEach(param -> registration.setInitParameter(param.name(), param.value()));
        });
    }

    private void addServlets(ApplicationContext context, ServletContext servletContext) {
        Map<String, Servlet> servlets = context.getBeansOfType(Servlet.class);
        servlets.forEach((name, servlet) -> {
            System.out.println(servlet.getClass());
            System.out.println(Arrays.toString(servlet.getClass().getAnnotations()));
            ServletRegistration.Dynamic registration = servletContext.addServlet(name, servlet);
            ServletBean servletBeen = servlet.getClass().getAnnotationsByType(ServletBean.class)[0];
            registration.addMapping(servletBeen.urlPatterns());
            Arrays.asList(servletBeen.initParams()).forEach(param -> registration.setInitParameter(param.name(), param.value()));
        });
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute(ApplicationContextUtils.ROOT_IOC_CONTAINER);
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) ApplicationContextUtils.getApplicationContext(sce.getServletContext());
        applicationContext.close();
    }
}
