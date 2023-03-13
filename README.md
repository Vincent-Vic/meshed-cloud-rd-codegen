# Meshed Cloud Codegen

基于freemarker模板引擎的代码生成器,主要实现构建研发的适配器、RPC接口定义和模型定义

## 推送制品库
```shell
mvn clean install org.apache.maven.plugins:maven-deploy-plugin:2.8:deploy -DskipTests
```

## 使用 Codegen

### 添加依赖

Maven
```xml
<dependency>
    <groupId>cn.meshed.cloud.rd</groupId>
    <artifactId>meshed-cloud-rd-codegen</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 配置YAML
```yaml
code:
  generate:
    package-mapping:
      List: java.util.List
      Set: java.util.Set
      DTO: com.alibaba.cola.dto.DTO
      Command: com.alibaba.cola.dto.Command
      ClientObject: com.alibaba.cola.dto.ClientObject
      Query: com.alibaba.cola.dto.Query
      PageQuery: com.alibaba.cola.dto.PageQuery
      MultiResponse: com.alibaba.cola.dto.MultiResponse
      SingleResponse: com.alibaba.cola.dto.SingleResponse
      PageResponse: com.alibaba.cola.dto.PageResponse
      Response: com.alibaba.cola.dto.Response
      NotBlank: javax.validation.constraints.NotBlank
      NotNull: javax.validation.constraints.NotNull
      NotEmpty: javax.validation.constraints.NotEmpty
      Size: javax.validation.constraints.Size
      Email: javax.validation.constraints.Email
      Max: javax.validation.constraints.Max
      Min: javax.validation.constraints.Min
      Pattern: javax.validation.constraints.Pattern
      ApiModelProperty: io.swagger.annotations.ApiModelProperty
    annotation-rule:
      Pattern:
        regexp: String
        message: String
      Size:
        min: Integer
        max: Integer
        message: String
      Max:
        value: Integer
        message: String
      Min:
        value: Integer
        message: String
      Email:
        regexp: String
        message: String

```

> code.generate.package-mapping : 配置类和包的对应关系，配置在这里的关系会被扫描导入，如动态生成的对象作为参数建议外部编写动态导入逻辑
> 
> code.generate.annotation-rule : 注解规则：注解类名 => 注解中参数的类型，目前仅支持 String 和 Integer 两种类型, Long 同样采用Integer
> 

### 注入执行器

在Spring Bean 服务中

```java
@Component
public class Service {

    @Autowired
    private GenerateClassExecute generateClassExecute;
}
```

### API 描述
目前仅提供GenerateClassExecute作为Spring关联对外操作的处理器，提供模型、控制器接口、dubbo rpc接口创建

```java
package cn.meshed.cloud.rd.codegen;

/**
 * <h1>生成执行器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface GenerateClassExecute {

    /**
     * 构建model
     *
     * @param objectModel 模型数据
     * @return 代码
     */
    String buildModel(ObjectModel objectModel);

    /**
     * 构建 Adapter 接口
     *
     * @param adapter 适配器数据
     * @return 代码
     */
    String buildAdapter(Adapter adapter);

    /**
     * 构建 RPC 接口
     *
     * @param rpc rpc数据
     * @return 代码
     */
    String buildRpc(Rpc rpc);
}

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