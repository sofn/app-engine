package com.junesix.api.frame.help.controllers;

import com.alibaba.fastjson.JSONObject;
import com.junesix.api.auth.annotation.ApiStatus;
import com.junesix.api.auth.annotation.AuthType;
import com.junesix.api.auth.annotation.BaseInfo;
import com.junesix.api.frame.annotation.Context;
import com.junesix.common.config.DefaultConfigLoader;
import com.junesix.common.context.RequestContext;
import com.junesix.common.utils.log.ApiLogger;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:19
 */
@RestController
@RequestMapping("/help")
public class HelpController {

    private static final Logger logger = LoggerFactory.getLogger(HelpController.class.getName());

    @BaseInfo(desc = "help-ping", status = ApiStatus.PUBLIC, needAuth = AuthType.OPTION)
    @RequestMapping(value = "/ping")
    public String ping(@Context RequestContext rc) {
        ApiLogger.info(rc.getRequestId());
        return "{\"apistatus\":1,\"result\":true}";
    }

    @RequestMapping(value = "/ping2")
    public String ping2(HttpServletRequest request) {
//        ApiLogger.info(rc.getRequestId());
        return "{\"apistatus\":1,\"result\":true}";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(@RequestParam String msg) {
        if (DefaultConfigLoader.getInstance().isDevEnv()) {
            try {
                Thread.sleep(RandomUtils.nextInt(1, 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        log();

        JSONObject result = new JSONObject();
        result.put("apistatus", 1);

        JSONObject msgJson = new JSONObject();
        msgJson.put("msg", msg);
        result.put("result", msgJson);

        return result.toJSONString();
    }

    public void log() {
        logger.debug("debug test");
        logger.info("info test");
        logger.warn("warn test");
        logger.error("error test");
    }
}
