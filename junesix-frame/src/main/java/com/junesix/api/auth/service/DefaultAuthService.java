package com.junesix.api.auth.service;

import com.junesix.api.auth.annotation.BaseInfo;
import com.junesix.api.auth.model.AuthRequest;
import com.junesix.api.auth.model.AuthResponse;
import com.junesix.common.context.RequestContext;
import com.junesix.common.context.ThreadLocalContext;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-8-30 00:22.
 */
@Service
public class DefaultAuthService extends RequestMappingHandlerAdapter implements AuthService {

    @Override
    public AuthResponse auth(AuthRequest request, Optional<BaseInfo> baseInfo) {
        //TODO
        return null;
    }


    @Override
    protected ModelAndView handleInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HandlerMethod handlerMethod) throws Exception {
        RequestContext context = ThreadLocalContext.getRequestContext();
        AuthRequest request = new AuthRequest(httpServletRequest);

        Method method = handlerMethod.getMethod();
        BaseInfo baseInfo = null;
        if (method.isAnnotationPresent(BaseInfo.class)) {
            baseInfo = method.getAnnotation(BaseInfo.class);
        }
        AuthResponse response = this.auth(request, Optional.ofNullable(baseInfo));

        return super.handleInternal(httpServletRequest, httpServletResponse, handlerMethod);
    }
}
