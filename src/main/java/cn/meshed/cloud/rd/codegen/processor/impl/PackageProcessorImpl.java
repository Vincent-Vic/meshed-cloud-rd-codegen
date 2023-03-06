package cn.meshed.cloud.rd.codegen.processor.impl;

import cn.meshed.cloud.rd.codegen.Field;
import cn.meshed.cloud.rd.codegen.Model;
import cn.meshed.cloud.rd.codegen.ObjectDefinition;
import cn.meshed.cloud.rd.codegen.model.JavaDefinition;
import cn.meshed.cloud.rd.codegen.model.JavaField;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import com.alibaba.cola.exception.SysException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <h1>包处理器实现类</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class PackageProcessorImpl implements PackageProcessor {

    private final Map<String,String> BASE_PACKAGE_MAPPING = new HashMap<String, String>(){{
       put("List","java.util.List");
       put("Set","java.util.Set");
       put("DTO","com.alibaba.cola.dto.DTO");
       put("NotBlank","javax.validation.constraints.NotBlank");
       put("NotNull","javax.validation.constraints.NotNull");
       put("NotEmpty","javax.validation.constraints.NotEmpty");
       put("Size","javax.validation.constraints.Size");
       put("Email","javax.validation.constraints.Email");
       put("Max","javax.validation.constraints.Max");
       put("Min","javax.validation.constraints.Min");
       put("Pattern","javax.validation.constraints.Pattern");
       put("ApiModelProperty","io.swagger.annotations.ApiModelProperty");
    }};

    /**
     * 通过java对象类型获取导入的包
     *
     * @param objectType 类型
     * @return 导入的包
     */
    @Override
    public String getBasePackage(String objectType) {
        if (StringUtils.isEmpty(objectType)){
            return null;
        }
        return BASE_PACKAGE_MAPPING.get(objectType);
    }

    /**
     * 扫描模型包
     *
     * @param javaModel 模型
     * @return 全部所需包列表
     */
    @Override
    public Set<String> scanModelPackage(JavaModel javaModel) {
        Set<String> modelPackage = scanObjectPackage(javaModel);
        //字段扫描
        if (CollectionUtils.isNotEmpty(javaModel.getFields())){
            for (JavaField javaField : javaModel.getFields()) {
                //SET 以及避免了重复，对于重复字段不影响
                //添加泛型类型的包
                addSet(modelPackage, getBasePackage(javaField.getGeneric()));
                //添加类型的包
                addSet(modelPackage, getBasePackage(javaField.getType()));
                //扫描注解
                Set<String> annotationPackages = scanAnnotation(javaField.getAnnotations());
                if (CollectionUtils.isNotEmpty(annotationPackages)){
                    modelPackage.addAll(annotationPackages);
                }
            }
        }
        return modelPackage;
    }

    /**
     * 扫描注解
     * @param annotations 注解列表
     */
    private Set<String> scanAnnotation(Set<String> annotations) {
        Set<String> packages = new HashSet<>();
        if (CollectionUtils.isNotEmpty(annotations)){
            for (String annotation : annotations) {
                String annotationName = annotation.substring(0,annotation.indexOf("("))
                        .replaceAll("@","");
                addSet(packages, getBasePackage(annotationName));
            }
        }
        return packages;
    }

    /**
     * 扫描基本对象
     *
     * @param javaDefinition 对象定义
     * @return 包列表
     */
    private Set<String> scanObjectPackage(JavaDefinition javaDefinition){
        Set<String> packages = null;
        if (CollectionUtils.isNotEmpty(javaDefinition.getImports())){
            packages = javaDefinition.getImports();
        } else {
            packages = new HashSet<>();
        }
        addSet(packages, getBasePackage(javaDefinition.getSuperClass()));
        return packages;
    }

    /**
     * 添加值到去重列表
     *
     * @param sets set 集合
     * @param value 值
     */
    private void addSet(Set<String> sets, String value) {
        if (sets == null){
            throw new SysException("addSet 集合不能为空");
        }
        if (StringUtils.isNotBlank(value)){
            sets.add(value);
        }
    }
}
