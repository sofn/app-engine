package com.junesix.frame.help;

import com.alibaba.fastjson.JSONObject;
import com.junesix.frame.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
public class HelpResourceTest {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate;
    private String host;

    @Before
    public void setup() {
        restTemplate = new TestRestTemplate();
        host = "http://localhost:" + port + "/";
    }

    @Test
    public void testPing() {
        JSONObject json = restTemplate.getForObject(host + "help/ping", JSONObject.class);
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
        headers.add("X-Matrix-UID", 1000 + "");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        JSONObject json = restTemplate.postForObject(host + "help/echo", request, JSONObject.class);
        assertThat(json).isNotNull();
        System.out.println(json.toJSONString());
        assertThat(json.getInteger("apistatus")).isEqualTo(1);
        assertThat(json.getJSONObject("result").getString("msg")).isEqualTo(msg);
    }

}
