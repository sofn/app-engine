/**
 *
 */
package com.junesix.auth.spi;


import com.junesix.auth.model.AuthExcepFactor;
import com.junesix.auth.model.AuthException;
import com.junesix.auth.model.AuthRequest;
import com.junesix.auth.service.AuthService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

/**
 * @author jolestar@gmail.com
 */
@Component("TrustHostSpi")
public class TrustHostSpi extends AbstractAuthSpi {

    public static final String SPI_NAME = "TrustHost";


    @Override
    public String getName() {
        return SPI_NAME;
    }

    @Override
    protected boolean checkCanAuth(AuthRequest request) {
        return request.getFrom() == AuthRequest.RequestFrom.INNER && request.getHeader(AuthService.MATRIX_UID_HEADER) != null;
    }

    @Override
    protected long doAuth(AuthRequest request) throws AuthException {
        long uid = NumberUtils.toLong(request.getHeader(AuthService.MATRIX_UID_HEADER), 0);
        if (uid == 0) {
            throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL, "Matrix uid header is empty.");
        }
        return uid;
    }

}
