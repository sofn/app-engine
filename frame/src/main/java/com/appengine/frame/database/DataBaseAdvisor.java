package com.appengine.frame.database;

import com.appengine.frame.context.RequestContext;
import com.appengine.frame.context.ThreadLocalContext;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-10-13 15:27
 */
@Aspect
@Service
@Log
public class DataBaseAdvisor {

    private String[] writeMethodPrefixs = new String[]{"add", "insert", "delete", "remove", "save", "update", "change", "modify"};
    private String[] queryMethodPrefixs = new String[]{"query", "select", "get", "list", "find","exists","count"};

    @Before("execution(* com.appengine..*Dao.*(..))")
    public void beforMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        RequestContext rc = ThreadLocalContext.getRequestContext();

        if (StringUtils.startsWithAny(methodName, writeMethodPrefixs)) {
            rc.setShouldReadMasterDB(true);
        } else if (StringUtils.startsWithAny(methodName, queryMethodPrefixs)) {
            rc.setShouldReadMasterDB(false);
        } else {
            log.warning("cannot found handle db method for methodName is: " + methodName);
            rc.setShouldReadMasterDB(true);
        }
    }
}
