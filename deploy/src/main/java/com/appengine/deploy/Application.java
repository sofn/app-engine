package com.appengine.deploy;

import com.appengine.common.config.DefaultConfigLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:17
 */
@Configuration
@EnableAutoConfiguration
@ImportResource("classpath:spring-context.xml")
public class Application {

    public static void main(String[] args) {
        DefaultConfigLoader.getInstance().getEnv();
        SpringApplication.run(Application.class, args);
    }

}
