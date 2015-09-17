package com.junesix.api.auth.spi;


import com.junesix.api.auth.model.AuthException;
import com.junesix.api.auth.model.AuthRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author jolestar@gmail.com
 */
public interface AuthSpi {

    public static final String AUTH_HEADER = "Authorization";

    public static final long DEFAULT_CACHE_TIME = TimeUnit.HOURS.toMillis(1);

    public String getName();

    public boolean canAuth(AuthRequest request);

    /**
     * 验证失败则抛出异常
     *
     * @param request 上下文
     * @return uid
     * @throws AuthException
     */
    public long auth(AuthRequest request) throws AuthException;

    /**
     * 认证后的额外检查
     *
     * @return uid 如果额外检查失败，抛出 AuthException
     */
    public void afterAuth(long uid, AuthRequest request) throws AuthException;
}
