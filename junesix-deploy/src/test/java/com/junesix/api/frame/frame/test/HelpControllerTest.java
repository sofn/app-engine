package com.junesix.api.frame.frame.test;

import com.junesix.api.frame.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-06-08 15:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
@Ignore
public class HelpControllerTest {
    RestTemplate template = new TestRestTemplate();

    @Test
    public void testHelpPing() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:8080/help/ping", String.class);
        System.out.println(response);
    }
}
