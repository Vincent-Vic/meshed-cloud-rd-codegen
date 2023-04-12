package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.model.JavaDefinition;
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
public class ObjectEnum extends ObjectDefinition {

    /**
     * 枚举值
     */
    private Set<ObjectEnumValue> enumValues;
}
