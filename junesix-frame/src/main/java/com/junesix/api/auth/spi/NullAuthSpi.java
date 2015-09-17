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

    private NullAuthSpi() {
        super(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.weibo.api.auth.spi.AuthSpi#getName()
     */
    @Override
    public String getName() {
        return SPI_NAME;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.weibo.api.auth.spi.AbstractAuthSpi#checkCanAuth(com.weibo.api.auth
     * .AuthRequest)
     */
    @Override
    protected boolean checkCanAuth(AuthRequest request) {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.weibo.api.auth.spi.AbstractAuthSpi#doAuth(com.weibo.api.auth.AuthRequest
     * )
     */
    @Override
    protected long doAuth(AuthRequest request) throws AuthException {
        throw new AuthException(AuthExcepFactor.E_USER_AUTHFAIL,"NullAuthSpi::doAuth");
    }

}
