package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.constant.ParameterType;
import cn.meshed.cloud.rd.codegen.constant.RequestType;
import cn.meshed.cloud.rd.codegen.impl.GenerateExecuteImpl;
import cn.meshed.cloud.rd.codegen.model.JavaInterface;
import cn.meshed.cloud.rd.codegen.model.JavaMethod;
import cn.meshed.cloud.rd.codegen.model.JavaModel;
import cn.meshed.cloud.rd.codegen.model.JavaParameter;
import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void generateAdapter(){
        Adapter adapter = new Adapter();
        adapter.setAuthor("Vincent Vic");
        adapter.setDescription("Test");
        adapter.setClassName("ProjectAdapter");
        adapter.setUri("/test");
        adapter.setPackageName("cn.meshed.cloud.rd.project");
        adapter.addImport("org.junit.Test");
        adapter.setVersion("1.0.0");
        adapter.setExplain("测试");
        Parameter parameter = new Parameter();
        parameter.setName("t1");
        parameter.setType("String");
        parameter.setExplain("111");
        parameter.setParameterType(ParameterType.REQUEST_BODY);
        Parameter parameter2 = new Parameter();
        parameter2.setName("t2");
        parameter2.setExplain("222");
        parameter2.setType("String");
        parameter2.setParameterType(ParameterType.PATH_VARIABLE);
        Set<Parameter> parameters = new HashSet<>();
        parameters.add(parameter);
        parameters.add(parameter2);
        AdapterMethod adapterMethod = new AdapterMethod();
        adapterMethod.setParameters(parameters);
        adapterMethod.setExplain("测试");
        Response response = new Response();
        response.setGeneric("SingleResponse");
        response.setSubGeneric("List");
        response.setDataType("String");
        adapterMethod.setResponse(response);
        adapterMethod.setName("test");
        adapterMethod.setUri("/tt");
        adapterMethod.setRequestType(RequestType.GET);
        adapter.setMethods(Collections.singletonList(adapterMethod));
        String code = generateExecute.buildAdapter(adapter);
        System.out.println(code);
    }
    @Test
    public void generateRpc(){
        Rpc rpc = new Rpc();
        rpc.setAuthor("Vincent Vic");
        rpc.setDescription("Test");
        rpc.setClassName("ProjectAdapter");
        rpc.setPackageName("cn.meshed.cloud.rd.project");
        rpc.addImport("org.junit.Test");
        rpc.setVersion("1.0.0");
        rpc.setExplain("测试");
        Parameter parameter = new Parameter();
        parameter.setName("t1");
        parameter.setType("String");
        parameter.setExplain("111");
        parameter.setParameterType(ParameterType.NONE);
        Parameter parameter2 = new Parameter();
        parameter2.setName("t2");
        parameter2.setExplain("222");
        parameter2.setType("String");
        parameter2.setParameterType(ParameterType.NONE);
        Set<Parameter> parameters = new HashSet<>();
        parameters.add(parameter);
        parameters.add(parameter2);
        AdapterMethod adapterMethod = new AdapterMethod();
        adapterMethod.setParameters(parameters);
        adapterMethod.setExplain("测试");
        Response response = new Response();
        response.setGeneric("SingleResponse");
        response.setSubGeneric("List");
        response.setDataType("String");
        adapterMethod.setResponse(response);
        adapterMethod.setName("test");
        adapterMethod.setUri("/tt");
        rpc.setMethods(Collections.singletonList(adapterMethod));
        String code = generateExecute.buildRpc(rpc);
        System.out.println(code);
    }
}