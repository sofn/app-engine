package com.junesix.auth.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

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

    @Resource(name = "rateRedis")
    private StringRedisTemplate redis;

    public long incrCount(TimeUnit time, Calendar calendar, String params) {
        String key = RATE_KEY_PREFIX + StringUtils.substring(time.name(), 0, 1).toLowerCase() + "_" + getCurrentTimeNum(calendar, time) + "_" + params;
        Long result = redis.execute((RedisConnection redisConnection) -> {
            return redisConnection.incr(key.getBytes());
        });
        if (result != null && result <= 2) {
            redis.expire(key, time.toSeconds(1) + 10, TimeUnit.SECONDS);
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
