package com.junesix.auth.annotation;

import java.util.concurrent.TimeUnit;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-10-08 12:03
 */
public @interface RateLimitRateConfig {
    /**
     * 单位时间最大请求数
     */
    int value();

    TimeUnit time() default TimeUnit.HOURS;
}
