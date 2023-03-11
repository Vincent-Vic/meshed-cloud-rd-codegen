package cn.meshed.cloud.rd.codegen.model;

import cn.meshed.cloud.utils.AssertUtils;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
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

    public JavaDefinition() {
        this.imports = new HashSet<>();
    }

    /**
     * 添加导入
     *
     * @param importName 导入包名
     */
    public void addImport(String importName) {
        if (StringUtils.isNotBlank(importName)) {
            this.imports.add(importName);
        }
    }

    /**
     * 批量导入
     *
     * @param imports 导入包名列表
     */
    public void addImports(Set<String> imports) {
        if (CollectionUtils.isNotEmpty(imports)) {
            this.imports.addAll(imports);
        }
    }

    /**
     * 校验
     */
    public void verification() {
        //包名中含类名设施处理特殊处理
        AssertUtils.isTrue(StringUtils.isNotBlank(this.className),"类名不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(this.packageName),"包名不能为空");
        //如果是类名结尾的默认被删除
        if (this.packageName.endsWith(this.className)){
            this.packageName = this.packageName.replace("."+this.className,"");
        }
    }
}
