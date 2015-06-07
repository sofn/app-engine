/**
 *
 */
package com.junesix.common.context;


/**
 * @author jolestar
 */
public class ThreadLocalContext extends InheritableThreadLocal<RequestContext> {

    private static RequestIDGenerator requestIdGenerator = DefaultRequestIdGenerator.getInstance();
    private static ThreadLocalContext instance = new ThreadLocalContext() {

        @Override
        protected RequestContext initialValue() {
            RequestContext rc = new RequestContext();
            rc.setRequestId(requestIdGenerator.nextId());
            return rc;
        }
    };

    public static ThreadLocalContext getInstance() {
        return instance;
    }

    public static void clear() {
        instance.remove();
    }
}
