/**
 *
 */
package com.junesix.common.context;


import javax.servlet.http.HttpServletRequest;

/**
 * @author jolestar
 */
public class ThreadLocalContext extends InheritableThreadLocal<RequestContext> {

    private static final RequestIDGenerator requestIdGenerator = DefaultRequestIdGenerator.getInstance();
    private static final ThreadLocalContext instance = new ThreadLocalContext() {
        @Override
        protected RequestContext initialValue() {
            return new RequestContext(requestIdGenerator.nextId());
        }
    };

    public static ThreadLocalContext getInstance() {
        return instance;
    }

    public static HttpServletRequest getServletRequest() {
        return ThreadLocalContext.getInstance().get().getOriginRequest();
    }

    public static RequestContext getRequestContext() {
        return instance.get();
    }

    public static void clear() {
        instance.remove();
    }
}
