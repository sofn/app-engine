package com.appengine.task.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-22 00:11.
 */
@Controller
@RequestMapping("/web/task")
public class WebTaskController {

    @RequestMapping(method = RequestMethod.GET)
    public String register() {
        return "task/index";
    }

}
