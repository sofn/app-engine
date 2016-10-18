package com.appengine.frame.spring;

import com.appengine.frame.spring.context.SwaggerIgnore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * @author sofn
 * @version 1.0 Created at: 2016-10-18 20:10
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket merchantStoreApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("internal-api")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select().apis(not(withClassAnnotation(SwaggerIgnore.class))) //SwaggerIngore的注解的controller将会被忽略
                .paths(or(regex("/users/.*")))
                .paths(or(regex("/task/.*")))
                .build()
                .apiInfo(testApiInfo());
    }

    private ApiInfo testApiInfo() {
        ApiInfo apiInfo = new ApiInfo("标题文档",//大标题
                "文档的详细说",//小标题
                "0.1",//版本
                "NO terms of service",
                "razorer@razorer.com",//作者
                "The Apache License, Version 2.0",//链接显示文字
                "www.razorer.com"//网站链接
        );

        return apiInfo;
    }
}