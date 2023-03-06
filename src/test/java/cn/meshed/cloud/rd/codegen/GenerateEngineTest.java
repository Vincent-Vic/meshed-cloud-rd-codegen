package cn.meshed.cloud.rd.codegen;


import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

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
    public void generate(){
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

}