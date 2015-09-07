package com.junesix.api.auth.service;

import com.junesix.api.auth.annotation.BaseInfo;
import com.junesix.api.frame.annotation.Context;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-8-30 00:22.
 */
@Service
public class DefaultAuthService extends RequestMappingHandlerAdapter implements AuthService {

    @Override
    public String getAuth() {
        return "auth";
    }


    @Override
    protected ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(BaseInfo.class)) {
            BaseInfo baseInfo = method.getAnnotation(BaseInfo.class);
            System.out.println("DefaultAuthService: " + baseInfo.desc());
        }

        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(Context.class)) {
                //TODO
            }
        }

        return super.handleInternal(request, response, handlerMethod);
    }
}
