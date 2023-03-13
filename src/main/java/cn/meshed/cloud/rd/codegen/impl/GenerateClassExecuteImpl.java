package cn.meshed.cloud.rd.codegen.impl;

import cn.meshed.cloud.rd.codegen.Adapter;
import cn.meshed.cloud.rd.codegen.GenerateClassExecute;
import cn.meshed.cloud.rd.codegen.ObjectDefinition;
import cn.meshed.cloud.rd.codegen.ObjectField;
import cn.meshed.cloud.rd.codegen.ObjectMethod;
import cn.meshed.cloud.rd.codegen.ObjectModel;
import cn.meshed.cloud.rd.codegen.ObjectParameter;
import cn.meshed.cloud.rd.codegen.Rpc;
import cn.meshed.cloud.rd.codegen.constant.ParameterType;
import cn.meshed.cloud.rd.codegen.model.JavaField;
import cn.meshed.cloud.rd.codegen.model.JavaInterface;
import cn.meshed.cloud.rd.codegen.model.JavaMethod;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.model.JavaParameter;
import cn.meshed.cloud.rd.codegen.processor.AnnotationProcessor;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import cn.meshed.cloud.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.meshed.cloud.rd.codegen.constant.Constant.ADAPTER;
import static cn.meshed.cloud.rd.codegen.constant.Constant.ANNOTATION_SIMPLE_PARAMETER_FORMAT;
import static cn.meshed.cloud.rd.codegen.constant.Constant.MODEL;
import static cn.meshed.cloud.rd.codegen.constant.Constant.RPC;

/**
 * <h1>生成执行器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
public class GenerateClassExecuteImpl implements GenerateClassExecute {

    private final GenerateEngine generateEngine;
    private final PackageProcessor packageProcessor;
    private final AnnotationProcessor annotationProcessor;

    /**
     * 构建model
     *
     * @param objectModel 模型数据
     * @return 代码
     */
    @Override
    public String buildModel(ObjectModel objectModel) {
        checkModel(objectModel);
        JavaModel javaModel = getJavaModel(objectModel);
        //处理字段
        handleFields(objectModel, javaModel);

        //扫描包
        javaModel.setImports(packageProcessor.scanModelPackage(javaModel));

        return generateEngine.generate(MODEL, javaModel);
    }

    /**
     * 校验模型
     *
     * @param objectModel 模型
     */
    private void checkModel(ObjectModel objectModel) {
        AssertUtils.isTrue(StringUtils.isNotBlank(objectModel.getPackageName()), "包名不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(objectModel.getClassName()), "类名不能为空");
        if (CollectionUtils.isNotEmpty(objectModel.getFields())) {
            objectModel.getFields().stream().filter(Objects::nonNull).forEach(objectField -> {
                AssertUtils.isTrue(StringUtils.isNotBlank(objectField.getType()), "字段类型不能为空");
                AssertUtils.isTrue(StringUtils.isNotBlank(objectField.getName()), "字段名称不能为空");
            });
        }
    }

    /**
     * 构建 Adapter 接口
     *
     * @param adapter 适配器数据
     * @return 代码
     */
    @Override
    public String buildAdapter(Adapter adapter) {
        checkAdapter(adapter);
        JavaInterface javaInterface = getBaseJavaInterface(adapter);
        if (CollectionUtils.isNotEmpty(adapter.getMethods())) {
            Set<JavaMethod> methods = adapter.getMethods().stream().filter(Objects::nonNull)
                    .map(this::buildMethodJavaMethod).collect(Collectors.toSet());
            javaInterface.setMethods(methods);
        }
        javaInterface.setUri(adapter.getUri());
        javaInterface.setImports(packageProcessor.scanJavaInterfacePackage(javaInterface));
        javaInterface.addImports(packageProcessor.scanAdapterMethodPackage(adapter.getMethods()));
        return generateEngine.generate(ADAPTER, javaInterface);
    }

    /**
     * 校验适配器参数
     *
     * @param adapter 适配器
     */
    private void checkAdapter(Adapter adapter) {
        checkObjectDefinition(adapter);
        if (CollectionUtils.isNotEmpty(adapter.getMethods())) {
            adapter.getMethods().stream().filter(Objects::nonNull).forEach(adapterMethod -> {
                AssertUtils.isTrue(adapterMethod.getRequestType() != null, "请求类型不能为空");
                AssertUtils.isTrue(adapterMethod.getObjectResponse() != null, "返回数据不能为空");
                AssertUtils.isTrue(StringUtils.isNotBlank(adapterMethod.getName()), "方法名称不能为空");
            });
        }
    }

    /**
     * 构建 RPC 接口
     *
     * @param rpc rpc数据
     * @return 代码
     */
    @Override
    public String buildRpc(Rpc rpc) {
        checkRpc(rpc);
        JavaInterface javaInterface = getBaseJavaInterface(rpc);
        if (CollectionUtils.isNotEmpty(rpc.getMethods())) {
            Set<JavaMethod> methods = rpc.getMethods().stream().filter(Objects::nonNull)
                    .map(this::buildMethodJavaMethod).collect(Collectors.toSet());
        }
        javaInterface.setImports(packageProcessor.scanJavaInterfacePackage(javaInterface));
        javaInterface.addImports(packageProcessor.scanMethodPackage(rpc.getMethods()));
        return generateEngine.generate(RPC, javaInterface);
    }

    /**
     * 校验RPC 参数
     *
     * @param rpc rpc
     */
    private void checkRpc(Rpc rpc) {
        checkObjectDefinition(rpc);
        if (CollectionUtils.isNotEmpty(rpc.getMethods())) {
            rpc.getMethods().stream().filter(Objects::nonNull).forEach(method -> {
                AssertUtils.isTrue(method.getObjectResponse() != null, "返回数据不能为空");
                AssertUtils.isTrue(StringUtils.isNotBlank(method.getName()), "方法名称不能为空");
            });
        }
    }

    /**
     * 校验对象定义
     *
     * @param objectDefinition 对象定义
     */
    private void checkObjectDefinition(ObjectDefinition objectDefinition) {
        AssertUtils.isTrue(StringUtils.isNotBlank(objectDefinition.getPackageName()), "包名不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(objectDefinition.getClassName()), "类名不能为空");
    }

    /**
     * 构建方法到java方法
     *
     * @param method 适配器方法
     * @return JavaMethod
     */
    private JavaMethod buildMethodJavaMethod(ObjectMethod method) {
        //转换出参数
        JavaMethod javaMethod = getBaseJavaMethod(method);
        if (CollectionUtils.isNotEmpty(method.getParameters())) {
            Set<JavaParameter> javaParameters = method.getParameters().stream().filter(Objects::nonNull)
                    .map(this::buildJavaParameter).collect(Collectors.toSet());
            javaMethod.setParameters(javaParameters);
        }
        return javaMethod;
    }

    /**
     * 得到适配器的基本方法数据转换
     *
     * @param method 适配器方法
     * @return JavaMethod
     */
    private JavaMethod getBaseJavaMethod(ObjectMethod method) {
        JavaMethod javaMethod = new JavaMethod();
        javaMethod.setExplain(method.getExplain());
        javaMethod.setResponse(method.getResponseData());
        javaMethod.setName(method.getName());
        javaMethod.setAnnotations(method.getAnnotations());
        javaMethod.verification();

        return javaMethod;
    }

    /**
     * 构建适配器参数到java参数
     *
     * @param objectParameter 适配器参数
     * @return JavaParameter
     */
    private JavaParameter buildJavaParameter(ObjectParameter objectParameter) {
        JavaParameter javaParameter = getBaseJavaParameter(objectParameter);
        //判断是否是路径参数
        if (ParameterType.PATH_VARIABLE == objectParameter.getParameterType()) {
            objectParameter.addAnnotation(String.format(ANNOTATION_SIMPLE_PARAMETER_FORMAT,
                    objectParameter.getParameterType().getAnnotation(), objectParameter.getName()));
        }
        javaParameter.setAnnotations(objectParameter.getAnnotations());
        return javaParameter;
    }

    /**
     * 得到适配器的基本参数数据转换
     *
     * @param objectParameter 参数
     * @return JavaParameter
     */
    private JavaParameter getBaseJavaParameter(ObjectParameter objectParameter) {
        JavaParameter javaParameter = new JavaParameter();
        javaParameter.setName(objectParameter.getName());
        javaParameter.setType(objectParameter.getType());
        javaParameter.setExplain(objectParameter.getExplain());
        javaParameter.verification();
        return javaParameter;
    }

    /**
     * 得到对象定义的基本接口数据转换
     *
     * @param objectDefinition 对象定义
     * @return JavaInterface
     */
    private JavaInterface getBaseJavaInterface(ObjectDefinition objectDefinition) {
        JavaInterface javaInterface = new JavaInterface();
        javaInterface.setAuthor(objectDefinition.getAuthor());
        javaInterface.setDescription(objectDefinition.getDescription());
        javaInterface.setClassName(objectDefinition.getClassName());
        javaInterface.setPackageName(objectDefinition.getPackageName());
        javaInterface.setImports(objectDefinition.getImports());
        javaInterface.setVersion(objectDefinition.getVersion());
        javaInterface.setExplain(objectDefinition.getExplain());
        javaInterface.verification();
        return javaInterface;
    }

    /**
     * 处理字段
     *
     * @param objectModel 模型
     * @param javaModel   java 模型
     */
    private void handleFields(ObjectModel objectModel, JavaModel javaModel) {
        if (CollectionUtils.isNotEmpty(objectModel.getFields())) {
            Set<JavaField> fields = objectModel.getFields().stream().filter(Objects::nonNull)
                    .map(this::toJavaField).collect(Collectors.toSet());
            javaModel.setFields(fields);
        }
    }

    /**
     * 转换 java 字段
     *
     * @param objectField 对象字段
     * @return JavaField
     */
    private JavaField toJavaField(ObjectField objectField) {
        JavaField javaField = new JavaField();
        javaField.setGeneric(objectField.getGeneric());
        javaField.setName(objectField.getName());
        javaField.setExplain(objectField.getExplain());
        javaField.setType(objectField.getType());
        //生成注解
        javaField.setAnnotations(annotationProcessor.generateModelFieldAnnotation(objectField));
        javaField.verification();
        return javaField;
    }

    /**
     * 基本java模型数据转换
     *
     * @param objectModel 模型
     * @return JavaModel
     */
    private JavaModel getJavaModel(ObjectModel objectModel) {
        JavaModel javaModel = new JavaModel();
        javaModel.setAuthor(objectModel.getAuthor());
        javaModel.setExplain(objectModel.getExplain());
        javaModel.setDescription(objectModel.getDescription());
        javaModel.setClassName(objectModel.getClassName());
        javaModel.setSuperClass(objectModel.getSuperClass());
        javaModel.setPackageName(objectModel.getPackageName());
        javaModel.setVersion(objectModel.getVersion());
        javaModel.setImports(objectModel.getImports());
        javaModel.verification();
        return javaModel;
    }
}
