package com.appengine.task.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 任务界面跳转Controller
 * <p>
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-22 00:11.
 */
@Controller
@RequestMapping("/web/task")
public class WebTaskController {

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "task/list";
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public String save() {
        return "task/save";
    }

}
