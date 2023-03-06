package cn.meshed.cloud.rd.codegen.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * <h1>基本java类的定义</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class JavaDefinition {

    /**
     * 作者
     */
    private String author;

    /**
     * 版本号
     */
    private String version;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 类名
     */
    private String className;

    /**
     * 父类名称
     */
    private String superClass;

    /**
     * 导入类
     */
    private Set<String> imports;

    /**
     * 对象中文解释
     */
    private String explain;

    /**
     * 详情
     */
    private String description;


}
