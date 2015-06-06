#junesix

### 环境配置
一共有三种环境dev、test、prod，不同环境会加载不同的配置文件
1、jvm启动加参数 -Dspring.profiles.active=dev
例如：java8 -jar -Dspring.profiles.active=dev junesix-deploy/build/libs/junesix-deploy-0.1.RELEASE.jar

### 可执行jar包
运行 gradle bootRepackage 会自动打可执行war包，目录：junesix-deploy/build/libs/junesix-deploy-0.1.RELEASE.jar

### 初始化数据库
执行scripts/init_mysql.sh

### 运行项目方式
1、执行 gradle run
2、执行运行Application
3、执行 ./gradlew run 不用安装gradle