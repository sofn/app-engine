package com.junesix.common.config;

public interface ConfigLoader {

    // 环境变量
    enum Env {
        dev,
        test,
        prod
    }

    Env getEnv();

    boolean isTestEnv();
}
