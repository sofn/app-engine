package com.junesix.api.help;

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
@ImportResource("classpath*:spring.xml")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
