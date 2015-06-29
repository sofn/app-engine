package com.junesix.api.frame.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-28 17:17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Context {

}
