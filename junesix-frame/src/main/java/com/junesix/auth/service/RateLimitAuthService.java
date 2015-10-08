package com.junesix.auth.service;

import com.junesix.auth.annotation.RateLimit;
import com.junesix.auth.annotation.RateLimitRateConfig;
import com.junesix.auth.annotation.RateLimitTypeConfig;
import com.junesix.auth.model.AuthException;
import com.junesix.auth.redis.RateLimitRedisDao;
import com.junesix.frame.context.RequestContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-08 22:00.
 */
@Service
public class RateLimitAuthService {
    @Resource
    private RateLimitRedisDao redisDao;

    public void auth(RequestContext rc, RateLimit rateLimit) throws AuthException {
        RateLimitTypeConfig[] configs = rateLimit.value();

        Calendar calendar = Calendar.getInstance();
        for (RateLimitTypeConfig config : configs) {
            for (RateLimitRateConfig rateConfig : config.rates()) {
                Long count = redisDao.incrCount(rateConfig.time(), calendar, config.value().getParamString(rc));
                if (count > rateConfig.value()) {
                    throw new AuthException(config.value().getExcepFactor());
                }
            }
        }
    }
}
