package com.appengine.deploy;

import com.appengine.common.config.DefaultConfigLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:17
 */
@Configuration
//多数据源不能自动配置
//@EnableAutoConfiguration
@ImportResource("classpath:spring-context.xml")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        DefaultConfigLoader.getInstance().getEnv();
        SpringApplication.run(Application.class, args);
    }

}
