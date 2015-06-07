/**
 *
 */
package com.junesix.common.config;

import com.junesix.common.utils.log.ApiLogger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author jolestar
 */
public class DefaultConfigLoader implements ConfigLoader {

    public static final String APP_ENV_VAR = "spring.profiles.active";
    public static final String APP_ENV_FILE = "/apps/conf/env";

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
                    //再通过环境变量配置文件判断
                    Path path = Paths.get(APP_ENV_FILE);
                    try {
                        String fileEnv = new String(Files.readAllBytes(path));
                        env = fileEnv.trim();
                    } catch (Exception e) {
                        ApiLogger.error(e.getMessage());
                    }
                }
                // 默认为本地环境
                if (env == null) {
                    envVar = Env.dev;
                } else {
                    envVar = Env.valueOf(env);
                }
                System.setProperty(APP_ENV_VAR, "dev");

                System.out.println("AppEnv :" + envVar);
                ApiLogger.info("AppEnv :" + envVar);
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
