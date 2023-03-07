package cn.meshed.cloud.rd.codegen.impl;

import cn.meshed.cloud.rd.codegen.Adapter;
import cn.meshed.cloud.rd.codegen.AdapterMethod;
import cn.meshed.cloud.rd.codegen.Field;
import cn.meshed.cloud.rd.codegen.GenerateExecute;
import cn.meshed.cloud.rd.codegen.Method;
import cn.meshed.cloud.rd.codegen.Model;
import cn.meshed.cloud.rd.codegen.ObjectDefinition;
import cn.meshed.cloud.rd.codegen.Parameter;
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
import cn.meshed.cloud.rd.codegen.processor.impl.AnnotationProcessorImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.PackageProcessorImpl;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.meshed.cloud.rd.codegen.constant.Constant.ANNOTATION_SIMPLE_PARAMETER_FORMAT;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class GenerateExecuteImpl implements GenerateExecute {

    private final GenerateEngine generateEngine;
    private final PackageProcessor packageProcessor;
    private final AnnotationProcessor annotationProcessor;

    public GenerateExecuteImpl() {
        this.generateEngine = new GenerateEngineImpl();
        this.packageProcessor = new PackageProcessorImpl();
        this.annotationProcessor = new AnnotationProcessorImpl();
    }

    /**
     * 构建model
     *
     * @param model 模型数据
     * @return 代码
     */
    @Override
    public String buildModel(Model model) {
        JavaModel javaModel = getJavaModel(model);
        //处理字段
        handleFields(model, javaModel);

        //扫描包
        javaModel.setImports(packageProcessor.scanModelPackage(javaModel));

        return generateEngine.generate("model", javaModel);
    }

    /**
     * 构建 Adapter 接口
     *
     * @param adapter 适配器数据
     * @return 代码
     */
    @Override
    public String buildAdapter(Adapter adapter) {
        JavaInterface javaInterface = getBaseJavaInterface(adapter);
        if (CollectionUtils.isNotEmpty(adapter.getMethods())) {
            List<JavaMethod> methods = adapter.getMethods().stream()
                    .map(this::buildMethodJavaMethod).collect(Collectors.toList());
            javaInterface.setMethods(methods);
        }
        javaInterface.setImports(packageProcessor.scanJavaInterfacePackage(javaInterface));
        javaInterface.addImports(packageProcessor.scanAdapterMethodPackage(adapter.getMethods()));
        return generateEngine.generate("adapter", javaInterface);
    }

    /**
     * 构建 RPC 接口
     *
     * @param rpc rpc数据
     * @return 代码
     */
    @Override
    public String buildRpc(Rpc rpc) {
        JavaInterface javaInterface = getBaseJavaInterface(rpc);
        if (CollectionUtils.isNotEmpty(rpc.getMethods())) {
            List<JavaMethod> methods = rpc.getMethods().stream()
                    .map(this::buildMethodJavaMethod).collect(Collectors.toList());
            javaInterface.setMethods(methods);
        }
        javaInterface.setImports(packageProcessor.scanJavaInterfacePackage(javaInterface));
        javaInterface.addImports(packageProcessor.scanMethodPackage(rpc.getMethods()));
        return generateEngine.generate("rpc", javaInterface);
    }

    /**
     * 构建方法到java方法
     *
     * @param method 适配器方法
     * @return JavaMethod
     */
    private JavaMethod buildMethodJavaMethod(Method method) {
        //转换出参数
        JavaMethod javaMethod = getBaseJavaMethod(method);
        if (CollectionUtils.isNotEmpty(method.getParameters())) {
            Set<JavaParameter> javaParameters = method.getParameters().stream()
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
    private JavaMethod getBaseJavaMethod(Method method) {
        JavaMethod javaMethod = new JavaMethod();
        javaMethod.setExplain(method.getExplain());
        javaMethod.setResponse(method.getResponseData());
        javaMethod.setName(method.getName());
        javaMethod.setAnnotations(method.getAnnotations());
        return javaMethod;
    }

    /**
     * 构建适配器参数到java参数
     *
     * @param parameter 适配器参数
     * @return JavaParameter
     */
    private JavaParameter buildJavaParameter(Parameter parameter) {
        JavaParameter javaParameter = getBaseJavaParameter(parameter);
        //判断是否是路径参数
        if (ParameterType.PATH_VARIABLE == parameter.getParameterType()) {
            parameter.addAnnotation(String.format(ANNOTATION_SIMPLE_PARAMETER_FORMAT,
                    parameter.getParameterType().getAnnotation(), parameter.getName()));
        }
        javaParameter.setAnnotations(parameter.getAnnotations());
        return javaParameter;
    }

    /**
     * 得到适配器的基本参数数据转换
     *
     * @param parameter 参数
     * @return JavaParameter
     */
    private JavaParameter getBaseJavaParameter(Parameter parameter) {
        JavaParameter javaParameter = new JavaParameter();
        javaParameter.setName(parameter.getName());
        javaParameter.setType(parameter.getType());
        javaParameter.setExplain(parameter.getExplain());
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
        return javaInterface;
    }

    /**
     * 处理字段
     *
     * @param model     模型
     * @param javaModel java 模型
     */
    private void handleFields(Model model, JavaModel javaModel) {
        if (CollectionUtils.isNotEmpty(model.getFields())) {
            List<JavaField> fields = new ArrayList<>();
            for (Field field : model.getFields()) {
                JavaField javaField = new JavaField();
                javaField.setGeneric(field.getGeneric());
                javaField.setName(field.getName());
                javaField.setExplain(field.getExplain());
                javaField.setType(field.getType());
                //生成注解
                javaField.setAnnotations(annotationProcessor.generateModelFieldAnnotation(field));
                fields.add(javaField);
            }
            javaModel.setFields(fields);
        }
    }

    /**
     * 基本java模型数据转换
     *
     * @param model 模型
     * @return JavaModel
     */
    private JavaModel getJavaModel(Model model) {
        JavaModel javaModel = new JavaModel();
        javaModel.setAuthor(model.getAuthor());
        javaModel.setExplain(model.getExplain());
        javaModel.setDescription(model.getDescription());
        javaModel.setClassName(model.getClassName());
        javaModel.setSuperClass(model.getSuperClass());
        javaModel.setPackageName(model.getPackageName());
        javaModel.setVersion(model.getVersion());
        javaModel.setImports(model.getImports());
        return javaModel;
    }
}
