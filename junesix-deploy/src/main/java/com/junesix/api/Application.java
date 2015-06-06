package com.junesix.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:17
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.junesix.api")
@ImportResource("classpath*:spring/*.xml")
public class Application {
    public static void main(String[] args) {
        String profile = System.getProperty("spring.profiles.active");
        if (StringUtils.isBlank(profile)) {
            System.err.println("System Property spring.profiles.active not set");
            System.setProperty("spring.profiles.active", "dev");
            profile = System.getProperty("spring.profiles.active");
        }
        System.out.println("profile: " + profile);
        System.out.println();
        SpringApplication.run(Application.class, args);
    }
}
