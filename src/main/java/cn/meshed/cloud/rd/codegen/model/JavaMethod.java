package cn.meshed.cloud.rd.codegen.model;

import lombok.Data;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class JavaMethod {

    /**
     * 对象中文解释
     */
    private String name;
    /**
     * 方法中文解释
     */
    private String explain;
    /**
     * 方法返回
     */
    private String response;

    /**
     * 注解
     */
    private Set<String> annotations;

    /**
     * 参数列表
     */
    private Set<JavaParameter> parameters;
}
