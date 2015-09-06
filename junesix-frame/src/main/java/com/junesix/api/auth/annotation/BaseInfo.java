package com.junesix.api.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author x-spirit
 * @author jolestar
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BaseInfo {

    public String desc();

    public ApiStatus status();

    public AuthType needAuth() default AuthType.REQUIRED;

    public boolean needSSL() default false;

    public boolean crossDomain() default false;
}
