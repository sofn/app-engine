package com.junesix.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junesix.common.context.ClientVersion;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-06-26 12:19
 */
public class RequestLogRecord {
    static final String pattern = "yyyy-MM-dd HH:mm:ss,S";
    static final String SPLIT = "\t";

    private String requestId;

    private Date date = new Date();

    private String api;

    /**
     * http method
     */
    private String method;

    /**
     * http response status
     */
    private int responseStatus;

    /**
     * 来源 appkey
     */
    private String source = "unknow";

    private String platform;

    private long uid;

    private Map<String, String[]> parameters = Collections.emptyMap();

    private String parameterString;

    private String response;

    private String userAgent;

    private ClientVersion clientVersion = ClientVersion.NULL;

    // TODO
    // /**
    // * 调用方ip，如果是 内网服务端调用，该ip是服务器ip， 否则该ip与用户ip一致
    // */
    // private String clientIp;

    /**
     * 用户ip,如果是内网服务器端调用，该ip是调用方通过 Api-RemoteIP机制传递的用户ip
     */
    private String ip;

    /**
     * 接口响应使用时间
     */
    private long useTime;

    /**
     * 响应大小，单位：字节
     */
    private long responseSize;

    private String xpage;

    private String xreferer;

    public RequestLogRecord() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        if (source != null) {
            this.source = source;
        }
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    public String getParameterString() {
        return parameterString;
    }

    public void setParameterString(String parameterString) {
        this.parameterString = parameterString;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ClientVersion getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(ClientVersion clientVersion) {
        if (clientVersion != null) {
            this.clientVersion = clientVersion;
        }
    }

    public long getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(long responseSize) {
        this.responseSize = responseSize;
    }

    public JSONObject toJSONObject() {
        return new JSONObject();
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(DateFormatUtils.format(date, pattern));
        buf.append(SPLIT);
        buf.append(this.requestId);
        buf.append(SPLIT);
        buf.append(api);
        buf.append(SPLIT);
        buf.append(this.method);
        buf.append(SPLIT);
        buf.append(this.xpage);
        buf.append(SPLIT);
        buf.append(this.xreferer);
        buf.append(SPLIT);
        buf.append(this.responseSize);
        buf.append(SPLIT);
        buf.append(this.responseStatus);
        buf.append(SPLIT);
        if (this.useTime <= 0) {
            this.useTime = System.currentTimeMillis() - this.date.getTime();
        }
        buf.append(this.useTime);
        buf.append(SPLIT);
        buf.append(StringUtils.isBlank(source) ? "unknow" : source);
        buf.append(SPLIT);
        buf.append(uid);
        buf.append(SPLIT);
        buf.append(this.parameterString);
        buf.append(SPLIT);
        buf.append(this.ip);
        buf.append(SPLIT);
        if (this.clientVersion != null) {
            buf.append(this.clientVersion.toString());
        } else {
            buf.append(ClientVersion.NULL.toString());
        }
        buf.append(SPLIT);
        buf.append(this.userAgent);
        buf.append(SPLIT);
        buf.append(this.response);
        return buf.toString();
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getXpage() {
        return xpage;
    }

    public void setXpage(String xpage) {
        this.xpage = xpage;
    }

    public String getXreferer() {
        return xreferer;
    }

    public void setXreferer(String xreferer) {
        this.xreferer = xreferer;
    }
}
