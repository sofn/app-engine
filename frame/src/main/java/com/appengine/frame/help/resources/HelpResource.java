package com.appengine.frame.help.resources;

import com.alibaba.fastjson.JSONObject;
import com.appengine.auth.annotation.*;
import com.appengine.common.config.DefaultConfigLoader;
import com.appengine.frame.context.RequestContext;
import org.apache.commons.lang3.RandomUtils;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:19
 */
@Monitored
@RestController
@RequestMapping("/help")
public class HelpResource {

    private static final Logger logger = LoggerFactory.getLogger(HelpResource.class);

    @BaseInfo(desc = "help-ping", status = ApiStatus.PUBLIC, needAuth = AuthType.OPTION)
    @RateLimit({
            @RateLimitTypeConfig(value = RateLimitType.IP, rates = {@RateLimitRateConfig(100)}),
            @RateLimitTypeConfig(value = RateLimitType.USER, rates = {
                    @RateLimitRateConfig(value = 100, time = TimeUnit.MINUTES),
                    @RateLimitRateConfig(value = 10000, time = TimeUnit.HOURS)})
    })
    @RequestMapping(value = "/ping")
    public JSONObject ping(RequestContext rc) {
        JSONObject result = new JSONObject();
        result.put("uid", rc.getCurrentUid());
        result.put("app_id", rc.getAppId());
        result.put("remote_ip", rc.getIp());
        return result;
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public JSONObject echo(@RequestParam String msg) {
        if (DefaultConfigLoader.getInstance().isDevEnv()) {
            try {
                Thread.sleep(RandomUtils.nextInt(1, 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JSONObject msgJson = new JSONObject();
        msgJson.put("msg", msg);

        return msgJson;
    }
}
