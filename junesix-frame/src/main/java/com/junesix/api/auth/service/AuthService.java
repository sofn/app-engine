package com.junesix.api.auth.service;

import com.junesix.api.auth.annotation.BaseInfo;
import com.junesix.api.auth.model.AuthRequest;
import com.junesix.api.auth.model.AuthResponse;

import java.util.Optional;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-8-30 00:22.
 */
public interface AuthService {
    String MATRIX_REMOTEIP_HEADER = "X-Matrix-RemoteIP";
    String MATRIX_UID_HEADER = "X-Matrix-UID";
    String MATRIX_APPID_HEADER = "X-Matrix-APPID";

    AuthResponse auth(AuthRequest request, Optional<BaseInfo> baseInfo);
}
