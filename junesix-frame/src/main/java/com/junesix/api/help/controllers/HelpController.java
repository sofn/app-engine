package com.junesix.api.help.controllers;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:19
 */
@RestController
@RequestMapping("/help")
public class HelpController {

    @RequestMapping(value = "/ping")
    public String ping() {
        return "{\"apistatus\":1,\"result\":true}";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.POST)
    public String echo(@RequestParam String msg) {
        JSONObject result = new JSONObject();
        result.put("apistatus", 1);

        JSONObject msgJson = new JSONObject();
        msgJson.put("msg", msg);
        result.put("result", msgJson);

        return result.toJSONString();
    }
}
