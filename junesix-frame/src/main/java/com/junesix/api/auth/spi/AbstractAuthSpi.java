package com.junesix.api.auth.spi;

import com.junesix.api.auth.model.AuthException;
import com.junesix.api.auth.model.AuthRequest;
import com.junesix.api.auth.provider.UserProvider;
import com.junesix.common.utils.log.ApiLogger;

import javax.annotation.Resource;

/**
 * @author jolestar@gmail.com
 */
public abstract class AbstractAuthSpi implements AuthSpi {


    protected UserProvider userProvider;

    @Resource
    public void setUserProvider(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @Override
    public boolean canAuth(AuthRequest request) {
        try {
            boolean result = this.checkCanAuth(request);
            if (ApiLogger.isTraceEnabled()) {
                ApiLogger.trace(this.getName() + " can auth:" + result);
            }
            return result;
        } catch (Exception e) {
            ApiLogger.error("check can auth error:", e);
            return false;
        }
    }

    protected abstract boolean checkCanAuth(AuthRequest request);

    @Override
    public long auth(AuthRequest request) throws AuthException {
        return this.doAuth(request);
    }

    /**
     * 子类需要实现本方法 认证失败抛异常
     */
    protected abstract long doAuth(AuthRequest request) throws AuthException;

    @Override
    public void afterAuth(long uid, AuthRequest request) {
        // default do nothing
    }
}
