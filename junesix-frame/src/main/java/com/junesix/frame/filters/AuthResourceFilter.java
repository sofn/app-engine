package com.junesix.frame.filters;

import com.junesix.auth.annotation.BaseInfo;
import com.junesix.auth.model.AuthException;
import com.junesix.auth.model.AuthRequest;
import com.junesix.auth.model.AuthResponse;
import com.junesix.auth.service.AuthService;
import com.junesix.common.context.ClientVersion;
import com.junesix.common.utils.GlobalConstants;
import com.junesix.frame.context.RequestContext;
import com.junesix.frame.context.ThreadLocalContext;
import com.junesix.frame.help.controllers.ErrorHandlerController;
import com.junesix.frame.spring.JsonResultValueHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author sofn
 */
@Service
public class AuthResourceFilter extends RequestMappingHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthResourceFilter.class);

    @Resource(name = "defaultAuthService")
    private AuthService authService;

    @Override
    protected ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response,
                                          HandlerMethod handlerMethod) throws Exception {
        if (StringUtils.equals(request.getRequestURI(), ErrorHandlerController.ERROR_PATH)) {
            return super.handleInternal(request, response, handlerMethod);
        }

        RequestContext context = ThreadLocalContext.getRequestContext();
        context.setOriginRequest(request);

        AuthRequest authRequest = new AuthRequest(request);

        Method method = handlerMethod.getMethod();
        BaseInfo baseInfo = null;
        if (method.isAnnotationPresent(BaseInfo.class)) {
            baseInfo = method.getAnnotation(BaseInfo.class);
        }
        AuthResponse authResponse;
        try {
            authResponse = authService.auth(authRequest, Optional.ofNullable(baseInfo));
        } catch (AuthException e) {
            LOGGER.debug("auth failed! path: " + request.getRequestURI() + " appId: " + request.getHeader(AuthService.MATRIX_APPID_HEADER)
                    + " version: " + ClientVersion.valueOf(request.getHeader(ClientVersion.VERSION_HEADER)));
            throw e;
        }

        context.setCurrentUid(authResponse.getUid());
        context.setAppId(authResponse.getAppId());
        context.setOfficialApp(authResponse.getAppId() == GlobalConstants.DEFAULT_APPID);
        context.setIp(authResponse.getIp());
        context.setPlatform(authResponse.getPlatform());
        context.setAttribute("auth_type", authResponse.getAuthedBy());
        context.setClientVersion(authResponse.getClientVersion());

        return super.handleInternal(request, response, handlerMethod);
    }
}
