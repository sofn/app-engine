package com.junesix.api.filters;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

/**
 * Authors: sofn
 * Version: 1.0  Created at 15-6-8 23:15.
 */
@Configuration
public class FilterConfigFactory extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean jsonFilterChain() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter jsonFilter = new JsonResponseFilter();
        registration.setFilter(jsonFilter);
        registration.setOrder(Integer.MAX_VALUE - 1);  //asc
        return registration;
    }

    @Bean
    public FilterRegistrationBean headerFilterChain() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter headerFilter = new HeaderResponseFilter();
        registration.setFilter(headerFilter);
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
}
