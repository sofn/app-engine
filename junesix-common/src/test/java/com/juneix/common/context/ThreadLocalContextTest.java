package com.juneix.common.context;

import com.junesix.common.context.RequestContext;
import com.junesix.common.context.ThreadLocalContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-29 23:58.
 */
public class ThreadLocalContextTest {

    @Test
    public void testThreadLocalContext() {
        ThreadLocalContext threadLocal = ThreadLocalContext.getInstance();
        RequestContext context = threadLocal.get();
        System.out.println(context.getRequestId());
        ThreadLocalContext.clear();
        RequestContext context2 = threadLocal.get();
        System.out.println(context2.getRequestId());
        Assert.assertNotSame(context.getRequestId(), context2.getRequestId());
    }

    @Test
    public void testThreadLocalContextMultiThread() {
        ThreadLocalContext threadLocal = ThreadLocalContext.getInstance();
        final RequestContext context = threadLocal.get();
        System.out.println(context.getRequestId());
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                RequestContext context2 = ThreadLocalContext.getInstance().get();
                System.out.println(context2.getRequestId());
                Assert.assertEquals(context.getRequestId(), context2.getRequestId());
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testThreadLocalContextThreadPool() throws InterruptedException, ExecutionException {
        ThreadLocalContext threadLocal = ThreadLocalContext.getInstance();
        final RequestContext context = threadLocal.get();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<RequestContext> future = executor.submit(() -> {
            Thread.sleep(1000);
            return ThreadLocalContext.getInstance().get();
        });
        ThreadLocalContext.clear();

        threadLocal = ThreadLocalContext.getInstance();
        RequestContext context2 = threadLocal.get();

        Future<RequestContext> future2 = executor.submit(() -> {
            Thread.sleep(1000);
            return ThreadLocalContext.getInstance().get();
        });
        Future<RequestContext> future3 = executor.submit(() -> {
            Thread.sleep(1000);
            return ThreadLocalContext.getInstance().get();
        });
        RequestContext context3 = future.get();
        RequestContext context4 = future2.get();
        RequestContext context5 = future3.get();
        System.out.println(context.getRequestId() + " " + System.identityHashCode(context));
        System.out.println(context2.getRequestId() + " " + System.identityHashCode(context2));
        System.out.println(context3.getRequestId() + " " + System.identityHashCode(context3));
        System.out.println(context4.getRequestId() + " " + System.identityHashCode(context4));
        System.out.println(context5.getRequestId() + " " + System.identityHashCode(context5));
    }
}
