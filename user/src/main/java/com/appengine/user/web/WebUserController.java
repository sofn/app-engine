package com.appengine.user.web;

import com.appengine.auth.annotation.AuthType;
import com.appengine.auth.annotation.BaseInfo;
import com.appengine.common.exception.EngineException;
import com.appengine.user.domain.User;
import com.appengine.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-18 00:09.
 */
@Controller
@RequestMapping("/web")
public class WebUserController {

    public static final String LOGIN_ERROR_KEY = "LOGIN_ERROR";
    public static final String REGISTER_ERROR_KEY = "REGISTER_ERROR";

    @Resource
    private UserService userService;

    @BaseInfo(needAuth = AuthType.OPTION)
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "account/register";
    }

    @BaseInfo(needAuth = AuthType.OPTION)
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        boolean result;
        try {
            result = userService.save(new User(username, password));
            if (!result) {
                request.setAttribute(REGISTER_ERROR_KEY, "yes");
            }
        } catch (EngineException e) {
            request.setAttribute(REGISTER_ERROR_KEY, "exists");
            result = false;
        }
        if (!result) {
            request.setAttribute("username", username);
            return "account/register";
        }
        return "welcome";
    }

    @BaseInfo(needAuth = AuthType.OPTION)
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "account/login";
    }

    @BaseInfo(needAuth = AuthType.OPTION)
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @RequestParam String username, @RequestParam String password) {
        User user = userService.login(username, password);
        if (user == null) {
            request.setAttribute(LOGIN_ERROR_KEY, "yes");
            request.setAttribute("username", username);
            return "account/login";
        }
        return "welcome";
    }

}
