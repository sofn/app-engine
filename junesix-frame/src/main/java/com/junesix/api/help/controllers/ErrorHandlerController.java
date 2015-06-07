package com.junesix.api.help.controllers;

import com.junesix.api.filters.ErrorAttributesHandler;
import com.junesix.common.exception.ExcepFactor;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String error(HttpServletRequest request, HttpServletResponse response) {
        return new ErrorAttributesHandler(ExcepFactor.E_API_NOT_EXIST).toJSONString();
    }

}
