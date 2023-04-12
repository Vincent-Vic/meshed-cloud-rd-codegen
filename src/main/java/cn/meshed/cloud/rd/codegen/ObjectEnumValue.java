package cn.meshed.cloud.rd.codegen;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class ObjectEnumValue implements Serializable, Comparable<ObjectEnumValue> {

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
     * 对象中文解释
     */
    private Integer value;
    /**
     * 对象中文解释
     */
    private String ext;

    @Override
    public int compareTo(ObjectEnumValue o) {
        assert value != null;
        return value.compareTo(o.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectEnumValue)) {
            return false;
        }
        ObjectEnumValue that = (ObjectEnumValue) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
