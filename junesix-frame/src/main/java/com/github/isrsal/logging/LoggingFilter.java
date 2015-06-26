/*
 * spring-mvc-logger logs requests/responses
 *
 * Copyright (c) 2013. Israel Zalmanov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.isrsal.logging;

import com.junesix.api.utils.RequestLogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class LoggingFilter extends OncePerRequestFilter {

    protected static final Logger logger = LoggerFactory.getLogger("REQUEST");
    private AtomicLong id = new AtomicLong(1);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        long requestId = id.incrementAndGet();
        request = new RequestWrapper(requestId, request);
        response = new ResponseWrapper(requestId, response);
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long endTime = System.currentTimeMillis();
            RequestLogRecord record = new RequestLogRecord();
            record.setRequestId(requestId + "");
            record.setIp(request.getRemoteHost());
            record.setUseTime(endTime - startTime);
            record.setApi(request.getRequestURI());
            record.setDate(new Date());
            record.setMethod(request.getMethod());
            record.setParameters(request.getParameterMap());

            if (!isMultipart(request)) {
                RequestWrapper requestWrapper = (RequestWrapper) request;
                try {
                    String charEncoding = requestWrapper.getCharacterEncoding() != null ? requestWrapper.getCharacterEncoding() :
                            "UTF-8";
                    record.setParameterString(new String(requestWrapper.toByteArray(), charEncoding));
                } catch (UnsupportedEncodingException e) {
                    logger.warn("Failed to parse request payload", e);
                }
            }

            record.setResponseStatus(response.getStatus());
            record.setResponse(new String(((ResponseWrapper) response).toByteArray(), response.getCharacterEncoding()));
            logger.info(record.toString());
        }
    }

    private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }
}
