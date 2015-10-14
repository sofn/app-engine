package com.appengine.user.rest;

import com.appengine.auth.annotation.ApiStatus;
import com.appengine.auth.annotation.AuthType;
import com.appengine.auth.annotation.BaseInfo;
import com.appengine.user.domain.User;
import com.appengine.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-02 22:08.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @BaseInfo(desc = "login", status = ApiStatus.PUBLIC, needAuth = AuthType.OPTION)
    public User add(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

}
