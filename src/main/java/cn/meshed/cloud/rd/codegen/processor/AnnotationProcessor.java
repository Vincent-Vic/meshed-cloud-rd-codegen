package cn.meshed.cloud.rd.codegen.processor;

import cn.meshed.cloud.rd.codegen.ObjectField;

import java.util.Set;

/**
 * <h1>注解处理器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface AnnotationProcessor {

    /**
     * 生成字段注解
     *
     * @param objectField 字段
     * @return 注解列表
     */
    Set<String> generateFieldAnnotation(ObjectField objectField);

    /**
     * 生成模型字段注解
     *
     * @param objectField 字段
     * @return 注解列表
     */
    Set<String> generateModelFieldAnnotation(ObjectField objectField);
}
