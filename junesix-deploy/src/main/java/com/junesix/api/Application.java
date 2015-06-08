package com.junesix.api;

import com.junesix.common.config.DefaultConfigLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:17
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.junesix.api")
@ImportResource("classpath*:spring/*.xml")
public class Application {

    @Bean
    public Filter filterRegistrationBean() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    public static void main(String[] args) {
        DefaultConfigLoader.getInstance().getEnv();
        SpringApplication.run(Application.class, args);
    }

}
