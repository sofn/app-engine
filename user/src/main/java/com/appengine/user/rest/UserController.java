package com.appengine.user.rest;

import com.appengine.auth.annotation.ApiStatus;
import com.appengine.auth.annotation.AuthType;
import com.appengine.auth.annotation.BaseInfo;
import com.appengine.frame.context.RequestContext;
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
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @BaseInfo(desc = "注册用户", status = ApiStatus.PUBLIC, needAuth = AuthType.OPTION)
    public boolean add(@RequestParam String username, @RequestParam String password) {
        return userService.save(new User(username, password));
    }

    @RequestMapping(value = "/show")
    @BaseInfo(desc = "显示用户信息", status = ApiStatus.PUBLIC, needAuth = AuthType.REQUIRED)
    public User show(RequestContext rc, @RequestParam(required = false, defaultValue = "0") long uid) {
        if (uid <= 0) {
            uid = rc.getCurrentUid();
        }
        return userService.get(uid);
    }

}
