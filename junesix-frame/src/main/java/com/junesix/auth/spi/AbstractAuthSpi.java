package com.junesix.auth.spi;

import com.junesix.auth.model.AuthRequest;
import com.junesix.auth.provider.UserProvider;
import com.junesix.frame.utils.log.ApiLogger;

import javax.annotation.Resource;

/**
 * @author jolestar@gmail.com
 */
public abstract class AbstractAuthSpi implements AuthSpi {

    @Resource
    protected UserProvider userProvider;

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
    public void afterAuth(long uid, AuthRequest request) {
        // default do nothing
    }
}
