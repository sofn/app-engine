package com.junesix.api.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import com.junesix.common.exception.ExcepFactor;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-8 00:32.
 */
@Component
public class ErrorAttributesHandler implements ErrorAttributes, JSONAware {

    private ExcepFactor excepFactor = ExcepFactor.E_DEFAULT;


    public ErrorAttributesHandler() {
    }

    public ErrorAttributesHandler(ExcepFactor excepFactor) {
        this.excepFactor = excepFactor;
    }

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean b) {
        LinkedHashMap<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("apistatus", 0);
        JSONObject msgJson = new JSONObject();
        msgJson.put("error_code", excepFactor.getErrorCode());
        msgJson.put("error_msg", excepFactor.getErrorMsg());
        errorAttributes.put("result", msgJson);
        return errorAttributes;
    }

    @Override
    public Throwable getError(RequestAttributes requestAttributes) {
        return null;
    }

    @Override
    public String toJSONString() {
        return JSON.toJSONString(getErrorAttributes(null, false));
    }
}
