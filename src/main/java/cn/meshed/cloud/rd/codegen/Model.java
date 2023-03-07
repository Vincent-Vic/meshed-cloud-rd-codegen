package cn.meshed.cloud.rd.codegen;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Model extends ObjectDefinition {

    /**
     * 字段
     */
    private List<Field> fields;


}
