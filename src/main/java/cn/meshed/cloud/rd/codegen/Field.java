package cn.meshed.cloud.rd.codegen;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <h1>字段</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 对象中文解释
     */
    private String explain;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段泛型
     */
    private String generic;

    /**
     * 字段注解规则
     */
    private String annotationJson;

    /**
     * 例子（非必须）
     */
    private String example;

    /**
     * 不能为空
     */
    private boolean nonNull;

}
