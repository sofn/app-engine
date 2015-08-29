package com.junesix.api.auth.service;

import org.springframework.stereotype.Service;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-8-30 00:22.
 */
@Service
public class DefaultAuthService implements AuthService {

    @Override
    public String getAuth() {
        return "auth";
    }
}
