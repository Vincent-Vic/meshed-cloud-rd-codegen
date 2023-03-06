package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.impl.GenerateExecuteImpl;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class GenerateExecuteTest {

    private GenerateExecute generateExecute;

    @Before
    public void init(){
        generateExecute = new GenerateExecuteImpl();
    }


    @Test
    public void buildModel() {
        Model model = new Model();
        model.setAuthor("Vincent Vic");
        model.setDescription("Test");
        model.setExplain("测试");
        model.setClassName("Test");
        model.setPackageName("cn.meshed.cloud.rd");
        model.addImport("org.junit.Test");
        model.setVersion("1.0.0");
        model.setSuperClass("Object");
        Field field = new Field();
        field.setExplain("姓名");
        field.setType("String");
        field.setNonNull(true);
        field.setName("name");
        field.setAnnotationJson("{\"Pattern\":{\"regexp\":\"abc\",\"message\":\"规则不匹配\"},\"Size\":{\"min\":22,\"max\":70,\"message\":\"不在范围\"},\"Max\":{\"value\":50,\"message\":\"最大50\"},\"Min\":{\"value\":0,\"message\":\"最小0\"},\"Email\":null}");
        model.setFields(Collections.singletonList(field));
        String code = generateExecute.buildModel(model);
        System.out.println(code);
    }
}