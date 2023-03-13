package cn.meshed.cloud.rd.codegen;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <h1>方法</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class ObjectMethod {

    /**
     * 对象中文解释
     */
    private String name;
    /**
     * 方法中文解释
     */
    private String explain;
    /**
     * 详情
     */
    private String description;
    /**
     * 方法返回
     */
    private ObjectResponse objectResponse;

    /**
     * 注解
     */
    @Setter(AccessLevel.NONE)
    private Set<String> annotations;

    /**
     * 参数列表
     */
    private Set<ObjectParameter> parameters;

    public ObjectMethod() {
        this.annotations = new HashSet<>();
    }

    /**
     * 添加注解
     *
     * @param annotation 注解
     */
    public void addAnnotation(String annotation) {
        this.annotations.add(annotation);
    }

    /**
     * 批量添加注解
     *
     * @param annotations 注解列表
     */
    public void addAnnotations(Set<String> annotations) {
        this.annotations.addAll(annotations);
    }

    /**
     * 返回类型封装数据
     */
    public String getResponseData() {
        return objectResponse == null ? "void" : objectResponse.toString();
    }

    /**
     * 名称和参数数量具有唯一性
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectMethod)) {
            return false;
        }
        ObjectMethod that = (ObjectMethod) o;
        int i = 0, j = 0;
        if (CollectionUtils.isNotEmpty(getParameters())) {
            i = getParameters().size();
        }
        if (CollectionUtils.isNotEmpty(that.getParameters())) {
            j = that.getParameters().size();
        }

        //名称和参数数量一样视为相同
        return getName().equals(that.getName()) && i == j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
