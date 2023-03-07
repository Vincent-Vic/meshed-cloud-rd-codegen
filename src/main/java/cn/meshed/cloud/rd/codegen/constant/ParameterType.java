package cn.meshed.cloud.rd.codegen.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@AllArgsConstructor
@Getter
@ToString
public enum ParameterType {

    /**
     * 无注解
     */
    NONE(null,null),
    /**
     * Get类型
     */
    PATH_VARIABLE("PathVariable","org.springframework.web.bind.annotation.PathVariable"),
    /**
     * Post类型
     */
    REQUEST_BODY("RequestBody","org.springframework.web.bind.annotation.RequestBody"),
    ;

    private final String annotation;
    private final String importName;
}