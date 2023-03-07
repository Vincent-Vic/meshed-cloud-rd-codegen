package cn.meshed.cloud.rd.codegen;


import cn.meshed.cloud.rd.codegen.model.JavaInterface;
import cn.meshed.cloud.rd.codegen.model.JavaMethod;
import cn.meshed.cloud.rd.codegen.model.JavaParameter;
import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class GenerateEngineTest {

    private GenerateEngine generateEngine;

    @Before
    public void init(){
        generateEngine = new GenerateEngineImpl();
    }

    @Test
    public void generateModel(){
        JavaModel javaModel = new JavaModel();
        javaModel.setAuthor("Vincent Vic");
        javaModel.setDescription("Test");
        javaModel.setClassName("Test");
        javaModel.setPackageName("cn.meshed.cloud.rd");
        javaModel.setImports(Collections.singleton("org.junit.Test"));
        javaModel.setVersion("1.0.0");
        javaModel.setExplain("测试");
        javaModel.setSuperClass("Object");
        String code = generateEngine.generate("model", javaModel);
        System.out.println(code);
    }

    @Test
    public void generateAdapter(){
        JavaInterface javaInterface = new JavaInterface();
        javaInterface.setAuthor("Vincent Vic");
        javaInterface.setDescription("Test");
        javaInterface.setClassName("Test");
        javaInterface.setPackageName("cn.meshed.cloud.rd");
        javaInterface.setImports(Collections.singleton("org.junit.Test"));
        javaInterface.setVersion("1.0.0");
        javaInterface.setExplain("测试");
        JavaParameter javaParameter = new JavaParameter();
        javaParameter.setName("t1");
        javaParameter.setType("String");
        javaParameter.setAnnotations(Collections.singleton("@PathVariable(\"projectKey\")"));
        javaParameter.setExplain("111");
        JavaParameter javaParameter2 = new JavaParameter();
        javaParameter2.setName("t2");
        javaParameter2.setExplain("222");
        javaParameter2.setType("String");
        javaParameter2.setAnnotations(Collections.singleton("@PathVariable(\"projectKey\")"));
        Set<JavaParameter> javaParameters = new HashSet<>();
        javaParameters.add(javaParameter);
        javaParameters.add(javaParameter2);
        JavaMethod javaMethod = new JavaMethod();
        javaMethod.setParameters(javaParameters);
        javaMethod.setExplain("测试");
        javaMethod.setResponse("void");
        javaMethod.setName("test");
        javaInterface.setMethods(Collections.singletonList(javaMethod));
        String code = generateEngine.generate("adapter", javaInterface);
        System.out.println(code);
    }
}