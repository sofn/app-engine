package com.appengine.frame.help;

import com.alibaba.fastjson.JSONObject;
import com.appengine.deploy.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@SpringBootConfiguration
public class HelpResourceTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPing() {
        JSONObject json = restTemplate.getForObject("/help/ping", JSONObject.class);
        assertThat(json).isNotNull();
        System.out.println(json.toJSONString());
        assertThat(json.getInteger("apistatus")).isEqualTo(1);
        assertThat(json.getJSONObject("result").getInteger("uid")).isEqualTo(0);
    }

    @Test
    public void testEcho() {
        String msg = "hello";

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("msg", msg);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Engine-UID", 1000 + "");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);


        JSONObject json = restTemplate.postForObject("/help/echo", request, JSONObject.class);
        assertThat(json).isNotNull();
        System.out.println(json.toJSONString());
        assertThat(json.getInteger("apistatus")).isEqualTo(1);
        assertThat(json.getJSONObject("result").getString("msg")).isEqualTo(msg);
    }

}
