package com.appengine.deploy;

import com.appengine.common.config.DefaultProfileLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-04-29 16:17
 */
@Configuration
@EnableSwagger2
//多数据源不能自动配置
//@EnableAutoConfiguration
@ImportResource("classpath:spring-context.xml")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        DefaultProfileLoader.getInstance().getEnv();
        SpringApplication.run(Application.class, args);
    }

}
