package cn.meshed.cloud.rd.codegen.model;

import lombok.Data;

import java.util.List;

/**
 * <h1>JAVA 接口</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class JavaInterface extends JavaDefinition{

    /**
     * 方法列表
     */
    private List<JavaMethod> methods;
}
