package com.appengine.frame.database;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-10-13 15:27
 */
@Aspect
public class DataBaseAdvisor {

    public DataBaseAdvisor() {
        System.out.println("DataBaseAdvisor");
    }

    @Before("execution(* com.appengine..*.*(..))")
    public void beforMethod(JoinPoint joinPoint) {
        System.out.println("Logging before " + joinPoint.getSignature().getName());
    }
}
