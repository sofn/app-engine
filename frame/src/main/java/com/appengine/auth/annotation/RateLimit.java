package com.appengine.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-10-08 12:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimit {

    boolean internalIgnore() default true; //内网默认不拦截

    RateLimitTypeConfig[] value();

}
