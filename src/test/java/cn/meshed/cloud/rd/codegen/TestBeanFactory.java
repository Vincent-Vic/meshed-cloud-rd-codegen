package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.config.GenerateAutoProperties;
import cn.meshed.cloud.rd.codegen.factory.TemplateFactory;
import cn.meshed.cloud.rd.codegen.impl.GenerateClassExecuteImpl;
import cn.meshed.cloud.rd.codegen.processor.AnnotationProcessor;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import cn.meshed.cloud.rd.codegen.processor.impl.AnnotationProcessorImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.PackageProcessorImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>测试工具Bean工厂</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class TestBeanFactory {

    private final static Map<String, String> BASE_PACKAGE_MAPPING = new HashMap<String, String>() {{
        put("List", "java.util.List");
        put("Set", "java.util.Set");
        put("DTO", "com.alibaba.cola.dto.DTO");
        put("Command", "com.alibaba.cola.dto.Command");
        put("ClientObject", "com.alibaba.cola.dto.ClientObject");
        put("Query", "com.alibaba.cola.dto.Query");
        put("PageQuery", "com.alibaba.cola.dto.PageQuery");
        put("MultiResponse", "com.alibaba.cola.dto.MultiResponse");
        put("SingleResponse", "com.alibaba.cola.dto.SingleResponse");
        put("PageResponse", "com.alibaba.cola.dto.PageResponse");
        put("Response", "com.alibaba.cola.dto.Response");
        put("NotBlank", "javax.validation.constraints.NotBlank");
        put("NotNull", "javax.validation.constraints.NotNull");
        put("NotEmpty", "javax.validation.constraints.NotEmpty");
        put("Size", "javax.validation.constraints.Size");
        put("Email", "javax.validation.constraints.Email");
        put("Max", "javax.validation.constraints.Max");
        put("Min", "javax.validation.constraints.Min");
        put("Pattern", "javax.validation.constraints.Pattern");
        put("ApiModelProperty", "io.swagger.annotations.ApiModelProperty");
    }};

    private final static Map<String,Map<String,String>> ANNOTATION_RULE = new HashMap<String,Map<String,String>>(){{
        put("Pattern",new HashMap<String,String>(){{
            put("regexp","String");
            put("message","String");
        }});
        put("Email",new HashMap<String,String>(){{
            put("regexp","String");
            put("message","String");
        }});
        put("Max",new HashMap<String,String>(){{
            put("value","Integer");
            put("message","String");
        }});
        put("Min",new HashMap<String,String>(){{
            put("value","Integer");
            put("message","String");
        }});
        put("Size",new HashMap<String,String>(){{
            put("min","Integer");
            put("max","Integer");
            put("message","String");
        }});
    }};
    public static GenerateAutoProperties newGenerateProperties(){
        GenerateAutoProperties generateAutoProperties = new GenerateAutoProperties();
        generateAutoProperties.setPackageMapping(BASE_PACKAGE_MAPPING);
        generateAutoProperties.setAnnotationRule(ANNOTATION_RULE);
        return generateAutoProperties;
    }

    public static GenerateEngine newGenerateEngine(){
        TemplateFactory templateFactory = new TemplateFactory();
        return new GenerateEngineImpl(templateFactory);
    }

    public static AnnotationProcessor newAnnotationProcessor(){
        return new AnnotationProcessorImpl(newGenerateProperties());
    }

    public static PackageProcessor newPackageProcessor(){
        return new PackageProcessorImpl(newGenerateProperties());
    }

    public static GenerateClassExecute newGenerateExecute(){
        return new GenerateClassExecuteImpl(newGenerateEngine(),newPackageProcessor(),newAnnotationProcessor());
    }
}
