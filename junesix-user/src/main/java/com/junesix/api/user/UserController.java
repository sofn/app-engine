package com.junesix.api.user;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.junesix.utils.collection.GlobalCollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-7 00:30.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/show")
    public String ping() {
        String msg = GlobalCollectionUtils.argsJoiner2.join(ImmutableList.of("aaa", "bbb", "ccc", "ddd"));

        JSONObject result = new JSONObject();
        result.put("apistatus", 1);
        result.put("result", msg);

        return result.toJSONString();
    }
}
