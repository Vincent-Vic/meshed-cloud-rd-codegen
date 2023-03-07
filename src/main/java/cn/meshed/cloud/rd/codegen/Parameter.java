package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.constant.ParameterType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static cn.meshed.cloud.rd.codegen.constant.Constant.ANNOTATION_FORMAT;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class Parameter {

    /**
     * 字段类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 注解列表
     */
    @Getter(AccessLevel.NONE)
    private Set<String> annotations;

    /**
     * 参数类型
     */
    private ParameterType parameterType;

    /**
     * 参数中文解释
     */
    private String explain;

    public Parameter() {
        this.annotations = new HashSet<>();
    }

    /**
     * 添加注解
     * @param annotation 注解
     */
    public void addAnnotation(String annotation) {
        this.annotations.add(annotation);
    }

    /**
     * 批量添加注解
     * @param annotations 注解列表
     */
    public void addAnnotations(Set<String> annotations) {
        this.annotations.addAll(annotations);
    }

    public Set<String> getAnnotations() {
        //参数类型存在且不是空和路径类型加入注解，路径注解特殊交给业务端处理
        if (this.parameterType != null && ParameterType.PATH_VARIABLE != this.parameterType
                && ParameterType.NONE != this.parameterType){
            addAnnotation(String.format(ANNOTATION_FORMAT,this.parameterType.getAnnotation()));
        }
        return this.annotations;
    }
}
