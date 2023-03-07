package cn.meshed.cloud.rd.codegen;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>方法</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class Method {

    /**
     * 对象中文解释
     */
    private String name;
    /**
     * 方法中文解释
     */
    private String explain;
    /**
     * 方法返回
     */
    private Response response;

    /**
     * 注解
     */
    @Setter(AccessLevel.NONE)
    private Set<String> annotations;

    /**
     * 参数列表
     */
    private Set<Parameter> parameters;

    public Method() {
        this.annotations = new HashSet<>();
    }

    /**
     * 添加注解
     * @param annotation 注解
     */
    public void addAnnotation(String annotation){
        this.annotations.add(annotation);
    }

    /**
     * 批量添加注解
     *
     * @param annotations 注解列表
     */
    public void addAnnotations(Set<String> annotations){
        this.annotations.addAll(annotations);
    }

    /**
     * 返回类型封装数据
     */
    public String getResponseData() {
        return response.toString();
    }
}
