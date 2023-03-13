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
 * <h1>模型</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JavaModel extends JavaDefinition {


    /**
     * 字段
     */
    @Setter(AccessLevel.NONE)
    private Set<JavaField> fields;

    public void setFields(Set<JavaField> fields) {
        if (CollectionUtils.isNotEmpty(fields)){
            this.fields = fields.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }

}
