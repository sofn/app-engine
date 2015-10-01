package com.junesix.api.auth.service;

import com.junesix.api.auth.annotation.AuthType;
import com.junesix.api.auth.annotation.BaseInfo;
import com.junesix.api.auth.model.AuthExcepFactor;
import com.junesix.api.auth.model.AuthException;
import com.junesix.api.auth.model.AuthRequest;
import com.junesix.api.auth.model.AuthResponse;
import com.junesix.api.auth.spi.AuthSpi;
import com.junesix.api.auth.spi.NullAuthSpi;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-8-30 00:22.
 */
@Service
public class DefaultAuthService implements AuthService, ApplicationContextAware, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAuthService.class);

    private ApplicationContext context;
    private List<AuthSpi> authSpis;
    private Map<String, AuthSpi> authSpiMap = new HashMap<>();

    @Override
    public AuthResponse auth(AuthRequest request, Optional<BaseInfo> baseInfo) {

        AuthType type = baseInfo.get().needAuth();
        AuthSpi spi = this.findAuthServiceSpi(request, type);
        long uid = 0;
        try {
            if (type.authFailThrowException() && !(type.equals(AuthType.GUEST))) {
                throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL);
            }
        } catch (AuthException e) {
            //TODO 全部抛出异常
            if (type.authFailThrowException() && (type != AuthType.OUTER || request.getFrom() != AuthRequest.RequestFrom.INNER)) {
                throw e;
            }
        }

        spi.afterAuth(uid, request);
        String remoteIp = request.getHeader(MATRIX_REMOTEIP_HEADER);
        if (remoteIp == null) {
            remoteIp = request.getRemoteIp();
        }

        String authType = spi.getName();

        AuthResponse response = new AuthResponse((String) request.getAttribute("platform"), uid, remoteIp, authType);

        try {
            LOGGER.info(String.format("auth %s %s %s %s", request.getRequestURI(), uid, response.getAuthedBy(), remoteIp));
        } catch (Exception e) {
            LOGGER.warn("DefaultAuthServer", e);
        }
        return response;
    }

    private AuthSpi findAuthServiceSpi(AuthRequest request, AuthType type) {
        AuthSpi authSpi = null;

        for (AuthSpi spi : this.authSpis) {
            if (spi.canAuth(request)) {
                authSpi = spi;
                break;
            }
        }

        //默认NullAuthSpi
        if (authSpi == null) {
            authSpi = this.getAuthSpi(NullAuthSpi.SPI_NAME);
        }

        return authSpi;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, AuthSpi> spis = this.context.getBeansOfType(AuthSpi.class);
        authSpis.addAll(spis.values().stream().collect(Collectors.toList()));

        for (Map.Entry<String, AuthSpi> entry : spis.entrySet()) {
            authSpiMap.put(StringUtils.lowerCase(entry.getKey()), entry.getValue());
        }

    }

    @SuppressWarnings("unchecked")
    public <T extends AuthSpi> T getAuthSpi(String name) {
        return (T) this.authSpiMap.get(name.toLowerCase());
    }

}
