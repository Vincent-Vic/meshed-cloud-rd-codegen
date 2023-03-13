package cn.meshed.cloud.rd.codegen;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObjectModel extends ObjectDefinition {

    /**
     * 字段 保证字段名称不重复
     */
    private Set<ObjectField> fields;

}
