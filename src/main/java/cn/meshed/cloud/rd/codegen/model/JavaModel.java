package cn.meshed.cloud.rd.codegen.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <h1>模型</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class JavaModel extends JavaDefinition {


    /**
     * 字段
     */
    private List<JavaField> fields;



}
