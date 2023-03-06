package cn.meshed.cloud.rd.codegen.impl;

import cn.meshed.cloud.rd.codegen.Field;
import cn.meshed.cloud.rd.codegen.GenerateExecute;
import cn.meshed.cloud.rd.codegen.Model;
import cn.meshed.cloud.rd.codegen.model.JavaField;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.processor.AnnotationProcessor;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import cn.meshed.cloud.rd.codegen.processor.impl.AnnotationProcessorImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.PackageProcessorImpl;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    private void handleFields(Model model, JavaModel javaModel) {
        if (CollectionUtils.isNotEmpty(model.getFields())){
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
