package cn.meshed.cloud.rd.codegen;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>基本java类的定义</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class ObjectDefinition {

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
     * 对象中文解释
     */
    private String explain;

    /**
     * 父类名称
     */
    private String superClass;

    /**
     * 导入类
     */
    @Setter(AccessLevel.NONE)
    private Set<String> imports;

    /**
     * 详情
     */
    private String description;

    public ObjectDefinition() {
        this.imports = new HashSet<>();
    }

    /**
     * 添加导包
     *
     * @param importName 导入包名称
     */
    public void addImport(String importName){
        if (StringUtils.isNotBlank(importName)){
            this.imports.add(importName);
        }
    }

    /**
     * 批量添加导包
     *
     * @param importNames 导入包列表
     */
    public void addImport(Set<String> importNames){
        if (CollectionUtils.isNotEmpty(importNames)){
            this.imports.addAll(importNames);
        }
    }


}
