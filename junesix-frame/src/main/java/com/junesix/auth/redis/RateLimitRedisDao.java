package com.junesix.auth.redis;

import com.junesix.auth.annotation.RateLimitType;
import com.sun.org.apache.xalan.internal.xsltc.dom.FilteredStepIterator;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-08 22:00.
 */
@Component
public class RateLimitRedisDao {

    private static final String RATE_KEY_PREFIX = "rate_";

    @Resource
    private Jedis redis;

    public long incrCount(TimeUnit time, Calendar calendar, String params) {
        String key = RATE_KEY_PREFIX + time.name() + "_" + getCurrentTimeNum(calendar, time) + params;
        Long result = redis.incr(key);
        if (result != null && result <= 2) {
            redis.expire(key, (int) time.toSeconds(1) + 10);
        }
        return result != null ? result : 0;
    }


    public int getCurrentTimeNum(TimeUnit timeUnit) {
        return getCurrentTimeNum(Calendar.getInstance(), timeUnit);
    }

    public int getCurrentTimeNum(Calendar calendar, TimeUnit timeUnit) {
        int result;
        switch (timeUnit) {
            case DAYS:
                result = calendar.get(Calendar.DAY_OF_YEAR);
                break;
            case HOURS:
                result = calendar.get(Calendar.HOUR_OF_DAY);
                break;
            case MINUTES:
                result = calendar.get(Calendar.MINUTE);
                break;
            case SECONDS:
                result = calendar.get(Calendar.SECOND);
                break;
            case MILLISECONDS:
                result = calendar.get(Calendar.MILLISECOND);
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

}
