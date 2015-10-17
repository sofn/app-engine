app-engine 是一个分布式的App后台快速开发框架,包含了基本的权限认证、日志处理、接口防刷、系统监控等基本功能。
此框架围绕分布式服务系统构建，微服务、去Session化、多数据源、主从分离。

### 技术栈：
1. Spring Boot / Spring MVC / Spring Data Jpa
2. Gradle
3. Java8
4. Logback
5. Lombok

### 功能列表： 
1. 认证方式： Basic、 Cookie、Header、内外网
2. 统一错误处理、统一Json格式模板
3. request log
4. 完善的系统监控
5. 接口频次拦截
6. 支持多数据源、主从分离

#### 环境配置
区分有三种环境dev、test、prod，不同环境会加载不同的配置文件
1.gradle启动同时要加参数 -Pprofile=dev

#### 数据库配置 
1. 如果安装了docker，直接执行 deploy/bin/脚本
2. 手动安装:  
    mysql: ip:127.0.0.1 port:3306 username:root  password:123  
    redis: ip:127.0.0.1 port:6379 password:无

#### 可执行jar包
运行 gradle bootRepackage 会自动打可执行war包，目录：deploy/build/libs/deploy-0.1.RELEASE.jar

#### 运行项目方式
1. 执行 gradle run
2. 执行运行Application
3. 执行 ./gradlew run 不用安装gradle

#### checkstyle findbugs
1. 指定 gradle check 
2. 在build/reports目录会生成相关报告文件

#### 监控
* 健康检查： http://localhost:7002/health
* 次数监控： http://localhost:7002/metrics
* APP信息： http://localhost:7002/info
* dump信息： http://localhost:7002/dump
* 环境信息： http://localhost:7002/env
* 性能监控： http://localhost:8080/javasimon
* 数据库监控： http://localhost:8080/druid
* Tomcat监控： http://localhost:7002/jolokia/read/Tomcat:type=Connector,port=8080

#### TODO
* 完善用户模块
* 完善注释
* 完善文档
* 添加单元测试、集成测试、压力测试
* 集成docker