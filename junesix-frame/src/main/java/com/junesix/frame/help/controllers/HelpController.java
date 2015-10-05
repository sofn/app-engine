package com.junesix.frame.help.controllers;

import com.alibaba.fastjson.JSONObject;
import com.junesix.auth.annotation.ApiStatus;
import com.junesix.auth.annotation.AuthType;
import com.junesix.auth.annotation.BaseInfo;
import com.junesix.common.config.DefaultConfigLoader;
import com.junesix.frame.context.RequestContext;
import org.apache.commons.lang3.RandomUtils;
import org.javasimon.aop.Monitored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:19
 */
@Monitored
@RestController
@RequestMapping("/help")
public class HelpController {

    private static final Logger logger = LoggerFactory.getLogger(HelpController.class.getName());

    @BaseInfo(desc = "help-ping", status = ApiStatus.PUBLIC, needAuth = AuthType.OPTION)
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
