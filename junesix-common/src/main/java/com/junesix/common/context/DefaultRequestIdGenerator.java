/**
 *
 */
package com.junesix.common.context;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jolestar
 */
public class DefaultRequestIdGenerator implements RequestIDGenerator {

    private final AtomicLong requestId = new AtomicLong(0);
    private String hostName;

    static class InstanceHolder {
        static DefaultRequestIdGenerator idGenerator = new DefaultRequestIdGenerator();
    }

    private DefaultRequestIdGenerator() {
        try {
            // hostname 一般是 machineName +"."+ domain
            // 这里只取machineName 即可
            this.hostName = java.net.InetAddress.getLocalHost().getHostName();
            int idx = this.hostName.indexOf('.');
            if (idx > 0) {
                this.hostName = this.hostName.substring(0, idx);
            }
        } catch (UnknownHostException e) {
            hostName = "localhost";
        }
    }

    public static DefaultRequestIdGenerator getInstance() {
        return InstanceHolder.idGenerator;
    }

    /*
     * (non-Javadoc)
     *
     * @see me.weimi.api.commons.context.RequestIDGenerator#nextId()
     */
    @Override
    public String nextId() {
        return this.hostName + "-" + requestId.getAndIncrement();
    }

}
