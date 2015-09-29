package com.junesix.api.frame.filters;

import com.junesix.api.frame.filters.utils.RequestLogRecord;
import com.junesix.api.frame.filters.utils.ResponseWrapper;
import com.junesix.api.frame.context.RequestContext;
import com.junesix.api.frame.context.ThreadLocalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestLogFilter extends OncePerRequestFilter {

    protected static final Logger logger = LoggerFactory.getLogger("REQUEST");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        RequestContext context = ThreadLocalContext.getRequestContext();
        context.setOriginRequest(request);
        response = new ResponseWrapper(response);
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long endTime = System.currentTimeMillis();
            RequestLogRecord record = new RequestLogRecord();
            record.setRequestId(context.getRequestId());
            record.setIp(request.getRemoteHost());
            record.setUseTime(endTime - startTime);
            record.setApi(request.getRequestURI());
            record.setMethod(request.getMethod());
            record.setParameters(request.getParameterMap());
            record.setResponseStatus(response.getStatus());
            record.setResponse(new String(((ResponseWrapper) response).toByteArray(), response.getCharacterEncoding()));
            logger.info(record.toString());
            ThreadLocalContext.clear();
        }
    }

    private boolean isMultipart(final HttpServletRequest request) {
        return request.getContentType() != null && request.getContentType().startsWith("multipart/form-data");
    }
}
