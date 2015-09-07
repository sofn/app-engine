package com.junesix.api.frame.spring;

import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-09-07 13:40
 */
@Service
public class EncodingPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            List<HttpMessageConverter<?>> convs = ((RequestMappingHandlerAdapter) bean).getMessageConverters();
            for (HttpMessageConverter<?> conv : convs) {
                if (conv instanceof StringHttpMessageConverter) {
                    ((StringHttpMessageConverter) conv).setSupportedMediaTypes(
                            Lists.newArrayList(new MediaType("application", "json", Charset.forName("UTF-8")),
                                    new MediaType("text", "html", Charset.forName("UTF-8"))));
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
        return bean;
    }
}