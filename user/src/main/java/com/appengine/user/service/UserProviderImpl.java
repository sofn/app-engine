package com.appengine.user.service;

import com.appengine.auth.model.AuthRequest;
import com.appengine.auth.provider.UserProvider;
import org.springframework.stereotype.Service;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-02 22:10.
 */
@Service
public class UserProviderImpl implements UserProvider {
    @Override
    public boolean isValidUser(long uid) {
        return true;
    }

    @Override
    public boolean checkCanAccess(AuthRequest request, long uid) {
        return true;
    }

    @Override
    public long authUser(String loginName, String password) {
        return 1000;
    }
}
