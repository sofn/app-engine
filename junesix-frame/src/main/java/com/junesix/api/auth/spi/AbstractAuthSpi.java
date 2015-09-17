/**
 *
 */
package com.junesix.api.auth.spi;

import com.junesix.api.auth.model.AuthExcepFactor;
import com.junesix.api.auth.model.AuthException;
import com.junesix.api.auth.model.AuthRequest;
import com.junesix.api.auth.provider.UserProvider;
import com.junesix.common.utils.log.ApiLogger;

import javax.annotation.Resource;

/**
 * @author jolestar@gmail.com
 */
public abstract class AbstractAuthSpi implements AuthSpi {

    /**
     * 是否检查该用户是否为微博用户
     */
    protected final boolean checkWeiboUser;
    protected UserProvider userProvider;

    public AbstractAuthSpi(boolean checkWeiboUser) {
        this.checkWeiboUser = checkWeiboUser;
    }


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
        long result = this.doAuth(request);
        if (this.checkWeiboUser && result > 0) {
            boolean isUser = this.userProvider.isValidUser(result);
            if (!isUser) {
                ApiLogger.warn("uid:" + result + " is not valid user.");
                throw new AuthException(AuthExcepFactor.E_USER_NOTOPEN);
            }
        }
        return result;
    }

    public long auth(AuthRequest request, boolean isMerchant) {
        if (isMerchant) {
            return this.doAuth(request);
        } else {
            return this.auth(request);
        }
    }

    /**
     * 子类需要实现本方法 认证失败抛异常
     *
     * @param request
     * @return
     */
    protected abstract long doAuth(AuthRequest request) throws AuthException;

    @Override
    public void afterAuth(long uid, AuthRequest request) {
        // default do nothing
    }
}
