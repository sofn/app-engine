package com.junesix.auth.spi;

import com.junesix.auth.model.AuthExcepFactor;
import com.junesix.auth.model.AuthException;
import com.junesix.auth.model.AuthRequest;
import org.springframework.stereotype.Component;

/**
 * @author jolestar
 */
@Component("NullSpi")
public class NullAuthSpi extends AbstractAuthSpi {

    public static final String SPI_NAME = "Null";

    @Override
    public String getName() {
        return SPI_NAME;
    }

    @Override
    protected boolean checkCanAuth(AuthRequest request) {
        return true;
    }

    @Override
    public long auth(AuthRequest request) throws AuthException {
        throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL, "NullAuthSpi::doAuth");
    }

}
