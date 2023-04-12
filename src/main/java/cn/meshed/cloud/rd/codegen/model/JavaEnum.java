package cn.meshed.cloud.rd.codegen.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JavaEnum extends JavaDefinition{

    /**
     * 枚举值
     */
    @Setter(AccessLevel.NONE)
    private Set<JavaEnumValue> enumValues;

    public void setEnumValues(Set<JavaEnumValue> enumValues) {
        if (CollectionUtils.isNotEmpty(enumValues)){
            this.enumValues = enumValues.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }
}
