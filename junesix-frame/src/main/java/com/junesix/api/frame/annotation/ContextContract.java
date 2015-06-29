package com.junesix.api.frame.annotation;

import com.junesix.common.context.RequestContext;
import com.junesix.common.context.ThreadLocalContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-28 17:20.
 */
@Aspect
@Component
public class ContextContract {
    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "&& args(com.junesix.common.context.RequestContext,..)")
    public void setContext(final JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            RequestContext context = ThreadLocalContext.getInstance().get();
            context.setCurrentUid(1000);
            //TODO 直接赋值无效
//            args[0] = context;
        }
    }
}
