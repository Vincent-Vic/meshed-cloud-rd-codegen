# Meshed Cloud Codegen

基于freemarker模板引擎的代码生成器,主要实现构建研发的适配器、RPC接口定义和模型定义

## 推送制品库
```shell
mvn clean install org.apache.maven.plugins:maven-deploy-plugin:2.8:deploy -DskipTests
```

## 使用 Codegen
Maven
```xml
<dependency>
    <groupId>cn.meshed.cloud.rd</groupId>
    <artifactId>meshed-cloud-rd-codegen</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## 主要依赖
|                            依赖                             |
|:---------------------------------------------------------:|
|             org.freemarker:freemarker:2.3.30              |
|             org.projectlombok:lombok:1.18.22              |
|      com.alibaba.cola:cola-component-exception:4.3.1      |
| org.springframework.boot:spring-boot-autoconfigure: 2.6.3 |
|         org.apache.commons:commons-lang3:3.12.0           |
|                cn.hutool:hutool-all:5.8.6                 |
|        org.apache.commons:commons-collections4:4.4        |

> 工程中依赖封装管理 (cn.meshed.cloud:meshed-cloud-common:1.0.0-SNAPSHOT)