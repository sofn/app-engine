package com.junesix.api.auth.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-09-17 22:06.
 */
public class AuthResponse implements Serializable {
    private static final long serialVersionUID = 5453139780159342985L;

    private final long uid;
    private final Map<String, Object> attributes;
    private final String ip;
    private final String authedBy;
    private final String platform;
    private final long mid;

    public AuthResponse(String platform, long uid, String ip, String authedBy, long mid) {
        this.platform = platform;
        this.uid = uid;
        this.mid = mid;
        this.attributes = new HashMap<>();
        this.ip = ip;
        this.authedBy = authedBy;
    }

    public long getUid() {
        return uid;
    }

    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return this.attributes.get(name);
    }

    public Set<Map.Entry<String, Object>> getAttributes() {
        return attributes.entrySet();
    }

    public String getIp() {
        return ip;
    }

    public String getAuthedBy() {
        return authedBy;
    }

    public String getPlatform() {
        return platform;
    }

    public long getMid() {
        return mid;
    }
}
