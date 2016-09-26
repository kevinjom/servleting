package com.github.kevinjom.servleting.servlet3x.spring;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebInitParam;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ServletBean {
    String[] urlPatterns() default {"/*"};

    WebInitParam[] initParams() default {};
}
