package com.appengine.user.resource;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.appengine.common.utils.collection.GlobalCollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-02 22:08.
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    @RequestMapping(value = "/show")
    public String ping() {
        String msg = GlobalCollectionUtils.argsJoiner2.join(ImmutableList.of("aaa", "bbb", "ccc", "ddd"));

        JSONObject result = new JSONObject();
        result.put("apistatus", 1);
        result.put("result", msg);

        return result.toJSONString();
    }
}
