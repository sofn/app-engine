package com.junesix.common.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;

/**
 * @author jolestar
 */
public class DefaultConfigLoader implements ConfigLoader {

    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultConfigLoader.class);
    public static final String APP_ENV_VAR = "profile";

    public static DefaultConfigLoader loader = new DefaultConfigLoader();

    private Env envVar;

    public static DefaultConfigLoader getInstance() {
        return loader;
    }


    @Override
    public Env getEnv() {
        if (envVar != null) {
            return envVar;
        }
        synchronized (this) {
            if (envVar == null) {
                //先通过环境变量判断
                String env = System.getenv(APP_ENV_VAR);
                if (env == null) {
                    try {
                        env = PropertiesLoaderUtils.loadAllProperties("application.properties").getProperty("profile");
                        env = StringUtils.strip(env);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
                // 默认为本地环境
                if (env == null) {
                    envVar = Env.test;
                } else {
                    envVar = Env.valueOf(env);
                }
                System.setProperty(APP_ENV_VAR, envVar.name());

                System.out.println("AppEnv :" + envVar);
                LOGGER.info("AppEnv :" + envVar);
            }
        }
        return envVar;
    }

    public boolean isTestEnv() {
        return this.getEnv() == Env.test || this.getEnv() == Env.dev;
    }

    public boolean isProdEnv() {
        return this.getEnv() == Env.prod;
    }

    public boolean isDevEnv() {
        return this.getEnv() == Env.dev;
    }


}
