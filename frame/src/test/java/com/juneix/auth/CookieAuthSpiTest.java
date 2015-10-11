package com.juneix.auth;

import com.junesix.auth.spi.CookieAuthSpi;
import com.junesix.auth.spi.MAuthSpi;
import org.junit.Test;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-04 22:45.
 */
public class CookieAuthSpiTest {
    @Test
    public void testCreate() {
        String mauth = CookieAuthSpi.generateCookie(1000);
        System.out.println(mauth);
    }
}
