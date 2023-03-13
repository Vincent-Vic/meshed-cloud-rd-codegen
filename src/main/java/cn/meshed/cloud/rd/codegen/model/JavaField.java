package cn.meshed.cloud.rd.codegen.model;

import cn.meshed.cloud.utils.AssertUtils;
import com.alibaba.cola.exception.SysException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>字段</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class JavaField implements Serializable, Comparable<JavaField> {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 对象中文解释
     */
    private String explain;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段泛型
     */
    private String generic;

    /**
     * 字段注解（以及生成好的注解）
     */
    @Setter(AccessLevel.NONE)
    private Set<String> annotations;

    /**
     * 设置注解排序处理 （空注解列表无法设置）
     *
     * @param annotations 注解列表
     */
    public void setAnnotations(Set<String> annotations) {
        if (CollectionUtils.isNotEmpty(annotations)) {
            this.annotations = annotations.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }

    /**
     * 校验
     */
    public void verification() {
        //包名中含类名设施处理特殊处理
        AssertUtils.isTrue(StringUtils.isNotBlank(this.type), "类型不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(this.name), "名称不能为空");
    }

    /**
     * 等于0的时候表示相等；
     * 等于1的时候表示大于；
     * 等于-1的时候表示小于；
     */
    @Override
    public int compareTo(JavaField javaField) {
        int i = this.name.compareTo(javaField.getName());
        if (i == 0) {
            throw new SysException("参数名称存在相同情况");
        }
        return i;
    }

    /**
     * 字段名称具有唯一性
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaField)) {
            return false;
        }
        JavaField javaField = (JavaField) o;
        return getName().equals(javaField.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
