package com.junesix.api.auth.spi;

import me.weimi.api.auth.AuthExcepFactor;
import me.weimi.api.auth.AuthException;
import me.weimi.api.auth.AuthRequest;
import me.weimi.api.auth.util.MerchantDecodeUtil;
import me.weimi.api.commons.encrypt.EncrypterException;
import me.weimi.api.commons.util.ApiLogger;
import me.weimi.api.commons.util.StringUtils;
import me.weimi.api.core.MatrixException;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Created by yanglu on 3/18/15.
 */
public class MerchantAuthSpi extends AbstractAuthSpi {

    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_HEADER_OTHER = "mauth";
    public static final String AUTH_PARAM = "mauth";
    public static final MerchantAuthSpi MERCHANT_AUTH_SPI = new MerchantAuthSpi();
    private static final String SPI_NAME = "MAuth";

    public MerchantAuthSpi() {
        super(true);
    }

    @Override
    protected boolean checkCanAuth(AuthRequest request) {
        return true;
    }

    @Override
    protected long doAuth(AuthRequest request) throws AuthException {
        String authHeader = StringUtils.isBlank(request.getHeader(AUTH_HEADER)) ? request.getHeader(AUTH_HEADER_OTHER) : request.getHeader(AUTH_HEADER);
        if (StringUtils.isBlank(authHeader)) {
            authHeader = request.getParameter(AUTH_PARAM);
        }
        if(StringUtils.isBlank(authHeader)) {
            throw new AuthException(AuthExcepFactor.E_MERCHANT_AUTH_AUTHORIZE_CODE_ILLEGAL);
        }

        String[] ss = authHeader.split(" ");
        if (ss.length != 2) {
            ApiLogger.error("Authorization header error, authHeader:" + authHeader);
            throw new AuthException(AuthExcepFactor.E_MERCHANT_AUTH_AUTHORIZE_CODE_ILLEGAL);
        }
        String authStr = ss[1];
        try {
            String decryptedStr = MerchantDecodeUtil.decode(request, authStr, null);

            if (decryptedStr == null) {
                throw new AuthException(AuthExcepFactor.E_MERCHANT_AUTH_AUTHORIZE_CODE_ILLEGAL);
            }
            long time = NumberUtils.toLong(decryptedStr.substring(0, 10));
            long now = System.currentTimeMillis() / 1000;
            if (time != 0 && time < now) {
                throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL, "mauth expires.");
            }
            long mid = NumberUtils.toLong(decryptedStr.substring(26));
            if (mid <= 0) {
                throw new AuthException(AuthExcepFactor.E_MERCHANT_AUTH_AUTHORIZE_CODE_ILLEGAL);
            }
            return mid;
        } catch (MatrixException e) {
            ApiLogger.error("mauth " + e.getMessage() + ", authHeader:" + authHeader);
            throw e;
        } catch (EncrypterException e) {
            ApiLogger.error("auth decrypt mauth token error,header:" + authHeader);
            throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL);
        } catch (RuntimeException e) {
            ApiLogger.error("auth fail mauth,header:" + authHeader, e);
            throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL);
        }
    }

    @Override
    public String getName() {
        return "MerchantMAuth";
    }
}
