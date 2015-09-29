/**
 *
 */
package com.junesix.api.auth.spi;

import com.junesix.api.auth.model.AuthExcepFactor;
import com.junesix.api.auth.model.AuthException;
import com.junesix.api.auth.model.AuthRequest;

/**
 * @author jolestar
 */
public class NullAuthSpi extends AbstractAuthSpi {

    public static final String SPI_NAME = "Null";

    public static final NullAuthSpi NULL_AUTH_SPI = new NullAuthSpi();

    @Override
    public String getName() {
        return SPI_NAME;
    }

    @Override
    protected boolean checkCanAuth(AuthRequest request) {
        return true;
    }

    @Override
    protected long doAuth(AuthRequest request) throws AuthException {
        throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL,"NullAuthSpi::doAuth");
    }

}
