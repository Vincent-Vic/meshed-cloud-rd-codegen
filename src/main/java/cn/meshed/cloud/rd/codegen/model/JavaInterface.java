package cn.meshed.cloud.rd.codegen.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>JAVA 接口</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JavaInterface extends JavaDefinition {

    /**
     * 方法列表 强制去重同名且同参数
     */
    @Setter(AccessLevel.NONE)
    private Set<JavaMethod> methods;


    /**
     * uri
     */
    private String uri;

    /**
     * 设置方法列表 方法列表排序处理 （方法列表为空无法进行操作）
     *
     * @param methods 方法列表
     */
    public void setMethods(Set<JavaMethod> methods) {
        if (CollectionUtils.isNotEmpty(methods)) {
            this.methods = methods.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }

}
