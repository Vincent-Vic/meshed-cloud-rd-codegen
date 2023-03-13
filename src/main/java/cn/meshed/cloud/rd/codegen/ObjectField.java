package cn.meshed.cloud.rd.codegen;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <h1>字段</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class ObjectField implements Serializable {

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

    /**
     * 不允许同名字段，哪怕类型或泛型不一样
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectField)) {
            return false;
        }
        ObjectField that = (ObjectField) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
