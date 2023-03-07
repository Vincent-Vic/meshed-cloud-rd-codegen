package cn.meshed.cloud.rd.codegen.processor.impl;

import cn.meshed.cloud.rd.codegen.AdapterMethod;
import cn.meshed.cloud.rd.codegen.Method;
import cn.meshed.cloud.rd.codegen.Parameter;
import cn.meshed.cloud.rd.codegen.config.GenerateProperties;
import cn.meshed.cloud.rd.codegen.model.JavaDefinition;
import cn.meshed.cloud.rd.codegen.model.JavaField;
import cn.meshed.cloud.rd.codegen.model.JavaInterface;
import cn.meshed.cloud.rd.codegen.model.JavaMethod;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.model.JavaParameter;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import com.alibaba.cola.exception.SysException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>包处理器实现类</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
public class PackageProcessorImpl implements PackageProcessor {

    private final GenerateProperties generateProperties;

    /**
     * 通过java对象类型获取导入的包
     *
     * @param objectType 类型
     * @return 导入的包
     */
    @Override
    public String getBasePackage(String objectType) {
        if (StringUtils.isEmpty(objectType) || generateProperties.getPackageMapping() == null) {
            return null;
        }
        return generateProperties.getPackageMapping().get(objectType);
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
        if (CollectionUtils.isNotEmpty(javaModel.getFields())) {
            for (JavaField javaField : javaModel.getFields()) {
                //SET 以及避免了重复，对于重复字段不影响
                //添加泛型类型的包
                addSet(modelPackage, getBasePackage(javaField.getGeneric()));
                //添加类型的包
                addSet(modelPackage, getBasePackage(javaField.getType()));
                //扫描注解
                Set<String> annotationPackages = scanAnnotation(javaField.getAnnotations());
                if (CollectionUtils.isNotEmpty(annotationPackages)) {
                    modelPackage.addAll(annotationPackages);
                }
            }
        }
        return modelPackage;
    }


    /**
     * 扫描接口包
     *
     * @param javaInterface 接口
     * @return 全部所需包列表
     */
    @Override
    public Set<String> scanJavaInterfacePackage(JavaInterface javaInterface) {
        Set<String> interfacePackages = scanObjectPackage(javaInterface);
        if (CollectionUtils.isNotEmpty(javaInterface.getMethods())) {
            for (JavaMethod method : javaInterface.getMethods()) {
                //方法扫描注解
                Set<String> methodAnnotationPackages = scanAnnotation(method.getAnnotations());
                if (CollectionUtils.isNotEmpty(methodAnnotationPackages)) {
                    interfacePackages.addAll(methodAnnotationPackages);
                }
                for (JavaParameter parameter : method.getParameters()) {
                    Set<String> annotationPackages = scanAnnotation(parameter.getAnnotations());
                    if (CollectionUtils.isNotEmpty(annotationPackages)) {
                        interfacePackages.addAll(annotationPackages);
                    }
                }
            }
        }

        return interfacePackages;
    }

    /**
     * 扫描方法
     *
     * @param methods 方法列表
     * @return 方法中导入的包
     */
    @Override
    public Set<String> scanMethodPackage(List<Method> methods) {
        Set<String> packsges = new HashSet<>();
        if (CollectionUtils.isNotEmpty(methods)) {
            for (Method method : methods) {

                for (Parameter parameter : method.getParameters()) {
                    addSet(packsges, parameter.getParameterType().getImportName());
                }
                addSet(packsges, getBasePackage(method.getResponse().getGeneric()));
                addSet(packsges, getBasePackage(method.getResponse().getSubGeneric()));
                addSet(packsges, getBasePackage(method.getResponse().getDataType()));
            }
        }
        return packsges;
    }

    /**
     * 扫描方法
     *
     * @param methods 方法列表
     * @return 方法中导入的包
     */
    @Override
    public Set<String> scanAdapterMethodPackage(List<AdapterMethod> methods) {
        Set<String> packsges = new HashSet<>();
        if (CollectionUtils.isNotEmpty(methods)) {
            for (AdapterMethod method : methods) {
                addSet(packsges, method.getRequestType().getImportName());
                for (Parameter parameter : method.getParameters()) {
                    addSet(packsges, parameter.getParameterType().getImportName());
                }
                addSet(packsges, getBasePackage(method.getResponse().getGeneric()));
                addSet(packsges, getBasePackage(method.getResponse().getSubGeneric()));
                addSet(packsges, getBasePackage(method.getResponse().getDataType()));
            }
        }
        return packsges;
    }

    /**
     * 扫描注解
     *
     * @param annotations 注解列表
     */
    private Set<String> scanAnnotation(Set<String> annotations) {
        Set<String> packages = new HashSet<>();
        if (CollectionUtils.isNotEmpty(annotations)) {
            for (String annotation : annotations) {
                int index = annotation.indexOf("(");
                String annotationName = annotation.substring(0, index < 0 ? annotation.length(): index)
                        .replaceAll("@", "");
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
    private Set<String> scanObjectPackage(JavaDefinition javaDefinition) {
        Set<String> packages = null;
        if (CollectionUtils.isNotEmpty(javaDefinition.getImports())) {
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
     * @param sets  set 集合
     * @param value 值
     */
    private void addSet(Set<String> sets, String value) {
        if (sets == null) {
            throw new SysException("addSet 集合不能为空");
        }
        if (StringUtils.isNotBlank(value)) {
            sets.add(value);
        }
    }
}
