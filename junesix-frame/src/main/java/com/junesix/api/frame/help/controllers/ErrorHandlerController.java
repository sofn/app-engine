package com.junesix.api.frame.help.controllers;

import com.junesix.api.frame.filters.GlobalExceptionHandler;
import com.junesix.common.exception.ExcepFactor;
import com.junesix.common.exception.MatrixException;
import com.junesix.common.exception.MatrixExceptionHelper;
import com.junesix.api.utils.log.ApiLogger;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 404处理
 *
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:19
 */
@RestController
public class ErrorHandlerController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public String error(HttpServletRequest request) {
        String path = (String) request.getAttribute("javax.servlet.error.request_uri");
        String errorMsg = (String) request.getAttribute("javax.servlet.error.message");
        int status = (int) request.getAttribute("javax.servlet.error.status_code");

        Exception exception = (Exception) request.getAttribute(GlobalExceptionHandler.GlobalExceptionAttribute);
        MatrixException apiException;
        if (exception != null && exception instanceof MatrixException) {
            apiException = (MatrixException) exception;
        } else if (status == 405) {
            apiException = MatrixExceptionHelper.localMatrixException(ExcepFactor.E_METHOD_ERROR);
        } else if (status == 404) {
            apiException = MatrixExceptionHelper.localMatrixException(ExcepFactor.E_API_NOT_EXIST);
        } else if (status == 415) {
            apiException = MatrixExceptionHelper.localMatrixException(ExcepFactor.E_UNSUPPORT_MEDIATYPE_ERROR, new Object[]{"unknow"});
        } else if (status >= 400 && status < 500) {
            apiException = MatrixExceptionHelper.localMatrixException(ExcepFactor.E_ILLEGAL_REQUEST, errorMsg);
        } else if (status == 503) {
            apiException = MatrixExceptionHelper.localMatrixException(ExcepFactor.E_SERVICE_UNAVAILABLE);
        } else {
            apiException = MatrixExceptionHelper.localMatrixException(ExcepFactor.E_DEFAULT);
            ApiLogger.error(errorMsg, exception);
        }
        return apiException.formatException(path);
    }
}
