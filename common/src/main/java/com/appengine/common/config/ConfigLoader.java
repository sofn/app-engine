package com.appengine.common.config;

/**
 * 定义运行时环境，注意和gradle环境区分
 * <p>
 * TODO 改成spring内置的运行时环境
 */
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
