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
public class JavaParameter {

    /**
     * 字段类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 注解列表
     */
    private Set<String> annotations;

    /**
     * 参数中文解释
     */
    private String explain;
}
