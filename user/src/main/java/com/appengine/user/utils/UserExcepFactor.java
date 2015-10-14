package com.appengine.user.utils;


import com.appengine.common.exception.ExcepFactor;
import com.appengine.common.utils.GlobalConstants;
import org.springframework.http.HttpStatus;

public class UserExcepFactor extends ExcepFactor {

    public static final UserExcepFactor ACCOUNT_EXISTS = new UserExcepFactor(HttpStatus.BAD_REQUEST, 1,
            "account exists", "账号已存在");

    public static final UserExcepFactor ACCOUNT_NOT_EXISTS = new UserExcepFactor(HttpStatus.BAD_REQUEST, 2,
            "account not exists", "账号不存在");

    protected UserExcepFactor(HttpStatus httpStatus, int errorCode, String errorMsg, String errorMsgCn) {
        super(GlobalConstants.USER_ID_AUTH, httpStatus, errorCode, errorMsg, errorMsgCn);
    }

}
