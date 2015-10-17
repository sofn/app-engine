package com.appengine.user.web;

import com.alibaba.fastjson.JSONObject;
import com.appengine.auth.annotation.AuthType;
import com.appengine.auth.annotation.BaseInfo;
import com.appengine.auth.spi.MAuthSpi;
import com.appengine.user.domain.User;
import com.appengine.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-18 00:09.
 */
@Controller
@RequestMapping("/web/login")
public class WebLoginController {

    public static final String LOGIN_ERROR_KEY = "LOGIN_ERROR";

    @Resource
    private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    @BaseInfo(needAuth = AuthType.OPTION)
    public String login() {
        return "account/login";
    }

    @RequestMapping(method = RequestMethod.POST)
    @BaseInfo(needAuth = AuthType.OPTION)
    public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        if (user == null) {
            request.setAttribute(LOGIN_ERROR_KEY, "yes");
            return "account/login";
        }
        return "welcome";
    }

}
