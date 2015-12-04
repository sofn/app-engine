package com.appengine.frame.filters;

import com.appengine.frame.context.RequestContext;
import com.appengine.frame.context.ThreadLocalContext;
import com.appengine.frame.utils.RequestLogRecord;
import com.appengine.frame.utils.ResponseWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestLogFilter implements Filter {

    protected static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getRequestURI();
        if (StringUtils.startsWith(path, "/webjars") || StringUtils.startsWith(path, "/static")) {
            filterChain.doFilter(request, response);
            return;
        }

        RequestContext context = ThreadLocalContext.getRequestContext();
        MDC.put("requestId", context.getRequestId());

        response = new ResponseWrapper(response);
        long startTime = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            if (StringUtils.equals("/error", request.getRequestURI()) || request.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION") == null) {
                long endTime = System.currentTimeMillis();
                RequestLogRecord record = new RequestLogRecord();
                record.setRequestId(context.getRequestId());
                record.setIp(request.getRemoteHost());
                record.setUseTime(endTime - startTime);
                record.setApi(context.getOriginRequest().getRequestURI());
                record.setMethod(request.getMethod());
                record.setParameters(request.getParameterMap());
                record.setResponseStatus(response.getStatus());
                record.setResponse(new String(((ResponseWrapper) response).toByteArray(), response.getCharacterEncoding()));
                //text/html不打印body
                if (!StringUtils.contains(response.getContentType(), "application/json")) {
                    record.setWriteBody(false);
                }
                MDC.put("CUSTOM_LOG", "request");
                logger.info(record.toString());
                MDC.remove("CUSTOM_LOG");
                ThreadLocalContext.clear();
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}