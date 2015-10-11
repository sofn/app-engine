package com.junesix.frame.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.junesix.frame.help.resources.ErrorHandlerResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * json统一拦截处理模板
 * <p>
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-03 00:15.
 */
@Service
public class JsonResultValueHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return FastJsonHttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //json保证apistatus在最前面
        JSONObject result = new JSONObject(true);
        if (StringUtils.equals(request.getURI().getPath(), ErrorHandlerResource.ERROR_PATH)) {
            result.put("apistatus", 0);
            body = JSON.parse((String) body);
        } else {
            result.put("apistatus", 1);
        }
        result.put("result", body);
        return result;
    }
}
