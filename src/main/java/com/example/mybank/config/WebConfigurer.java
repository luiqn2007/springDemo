package com.example.mybank.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Enumeration;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyRequestHandlerInterceptor());
    }
}

class MyRequestHandlerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        LOGGER.info("HTTP method --> {}", request.getMethod());
        Enumeration<String> requestName = request.getParameterNames();
        while (requestName.hasMoreElements()) {
            String name = requestName.nextElement();
            LOGGER.info("    Parameter name --> {}", name);
            LOGGER.info("    Parameter value --> {}", request.getParameter(name));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        LOGGER.info("Status code --> {}", response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.info("Request processing complete.");
    }
}
