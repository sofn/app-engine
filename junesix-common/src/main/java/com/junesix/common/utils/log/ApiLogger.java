package com.junesix.common.utils.log;

import com.alibaba.fastjson.JSONObject;
import com.junesix.common.config.DefaultConfigLoader;
import com.junesix.common.context.ThreadLocalContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-7 21:00.
 */
public class ApiLogger {
    public static long MC_FIRE_TIME = 200; // MC操作超时

    public static long DB_FIRE_TIME = 500; // DB操作超时

    public static long REDIS_FIRE_TIME = 300; // Redis操作超时

    private static Logger log = Logger.getLogger("debug");
    private static Logger infoLog = Logger.getLogger("info");
    private static Logger warnLog = Logger.getLogger("warn");
    private static Logger errorLog = Logger.getLogger("error");
    private static Logger paymentLog = Logger.getLogger("payment");

    private static Logger fireLog = Logger.getLogger("fire");

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LogManager.shutdown();
            }
        });
    }

    /**
     * perf4j log
     */
    private static final Logger prefLogger = Logger.getLogger("perf4j");

    public static boolean isTraceEnabled() {
        return log.isTraceEnabled() && !DefaultConfigLoader.getInstance().isProdEnv();
    }

    public static boolean isDebugEnabled() {
        return log.isDebugEnabled() && !DefaultConfigLoader.getInstance().isProdEnv();
    }

    public static void trace(Object msg) {
        log.trace(formatMsg(msg));
    }

    public static void trace(String tag, Object msg) {
        log.trace(formatMsg(tag, msg));
    }

    public static void debug(Object msg) {
        if (log.isDebugEnabled() && !DefaultConfigLoader.getInstance().isProdEnv()) {
            log.debug(formatMsg(msg));
        }
    }

    private static String formatMsg(Object msg) {
        return String.format("%s\t%s", ThreadLocalContext.getInstance().get().getRequestId(), msg == null ? "null" : msg.toString());
    }

    private static String formatMsg(String tag, Object msg) {
        return String.format("%s\t%s\t%s", ThreadLocalContext.getInstance().get().getRequestId(), tag, msg == null ? "null" : msg.toString());
    }

    public static void debug(String tag, Object msg) {
        if (msg == null) {
            return;
        }
        //非生产环境打印debug日志
        if (log.isDebugEnabled() && !DefaultConfigLoader.getInstance().isProdEnv()) {
            log.debug(formatMsg(tag, msg));
        }
    }

    /**
     * @param resourceType 资源类型
     * @param resourceId   资源id 如 服务器ip端口，接口的 url地址等
     * @param useTime      耗费时间
     */
    public static void fireSlow(String resourceType, String resourceId, long useTime) {
        fireSlow(resourceType, resourceId, useTime, null);
    }

    public static void fireSlow(String resourceType, String resourceId, long useTime, JSONObject ext) {
        fireLog.warn(String.format("%s\t%s\t%s\t%s\t%s\t%s", ThreadLocalContext.getInstance().get().getRequestId(), resourceType, resourceId, "slow", useTime, ext == null ? "" : ext.toJSONString()));
    }

    /**
     * @param resourceType 资源类型
     * @param resourceId   资源id
     * @param msg          错误消息
     * @param e            exception
     */
    public static void fireError(String resourceType, String resourceId, String msg, Exception e) {
        fireLog.error(String.format("%s\t%s\t%s\t%s\t%s\t%s", ThreadLocalContext.getInstance().get().getRequestId(), resourceType, resourceId, "error", msg, e.getClass().getName()));
    }

    public static void info(Object msg) {
        if (msg == null) {
            return;
        }
        if (infoLog.isInfoEnabled()) {
            infoLog.info(formatMsg(msg));
        }
    }

    public static void payment(Object msg) {
        if (msg == null) {
            return;
        }
        paymentLog.info(formatMsg(msg));
    }

    public static void info(String tag, Object msg) {
        if (msg == null) {
            return;
        }
        if (infoLog.isInfoEnabled()) {
            infoLog.info(formatMsg(tag, msg));
        }
    }

    public static void warn(Object msg) {
        warnLog.warn(formatMsg(msg));
    }

    // public static void warn(String tag,Object msg) {
    // if(msg == null){
    // return;
    // }
    // warnLog.warn(tag+"\t"+msg.toString());
    // }

    // public static void warn(Object msg, Throwable e) {
    // warnLog.warn(formatMsg(msg), e);
    // }

    public static void error(Object msg) {
        if (msg instanceof Throwable) {
            errorLog.error(formatMsg(((Throwable) msg).getMessage()), (Throwable) msg);
        } else {
            errorLog.error(formatMsg(msg));
        }
    }

    public static void error(Object msg, Throwable e) {
        errorLog.error(formatMsg(msg), e);
    }

}
