package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.constant.ParameterType;
import cn.meshed.cloud.rd.codegen.constant.RequestType;
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
public class GenerateClassExecuteTest {

    private GenerateClassExecute generateClassExecute;

    @Before
    public void init() {

        generateClassExecute = TestBeanFactory.newGenerateExecute();
    }


    @Test
    public void buildModel() {
        ObjectModel objectModel = new ObjectModel();
        objectModel.setAuthor("Vincent Vic");
        objectModel.setDescription("Test");
        objectModel.setExplain("测试");
        objectModel.setClassName("Test");
        objectModel.setPackageName("cn.meshed.cloud.rd");
        objectModel.addImport("org.junit.Test");
        objectModel.setVersion("1.0.0");
        objectModel.setSuperClass("Object");
        ObjectField objectField = new ObjectField();
        objectField.setExplain("姓名");
        objectField.setType("String");
        objectField.setNonNull(true);
        objectField.setName("name");
        objectField.setAnnotationJson("{\"Pattern\":{\"regexp\":\"abc\",\"message\":\"规则不匹配\"},\"Size\":{\"min\":22,\"max\":70,\"message\":\"不在范围\"},\"Max\":{\"value\":50,\"message\":\"最大50\"},\"Min\":{\"value\":0,\"message\":\"最小0\"},\"Email\":null}");
        objectModel.setFields(Collections.singleton(objectField));
        String code = generateClassExecute.buildModel(objectModel);
        System.out.println(code);
    }

    public void generateAdapter() {
        Adapter adapter = new Adapter();
        adapter.setAuthor("Vincent Vic");
        adapter.setDescription("Test");
        adapter.setClassName("ProjectAdapter");
        adapter.setUri("/test");
        adapter.setPackageName("cn.meshed.cloud.rd.project.ProjectAdapter");
        adapter.addImport("org.junit.Test");
        adapter.setVersion("1.0.0");
        adapter.setExplain("测试");
        AdapterMethod adapterMethod = buildMockAdapterMethod("save");
        AdapterMethod adapterMethod2 = buildMockAdapterMethod("edit");
        AdapterMethod adapterMethod3 = buildMockAdapterMethod("details");
        Set<AdapterMethod> adapterMethods = new HashSet<>();
        adapterMethods.add(adapterMethod);
        adapterMethods.add(adapterMethod2);
        adapterMethods.add(adapterMethod3);
        adapter.setMethods(adapterMethods);
        String code = generateClassExecute.buildAdapter(adapter);
        System.out.println(code);
    }

    private AdapterMethod buildMockAdapterMethod(String methodName) {
        ObjectParameter objectParameter = new ObjectParameter();
        objectParameter.setName("t1");
        objectParameter.setType("String");
        objectParameter.setExplain("111");
        objectParameter.setParameterType(ParameterType.REQUEST_BODY);
        ObjectParameter objectParameter2 = new ObjectParameter();
        objectParameter2.setName("t2");
        objectParameter2.setExplain("222");
        objectParameter2.setType("String");
        objectParameter2.setParameterType(ParameterType.PATH_VARIABLE);
        Set<ObjectParameter> objectParameters = new HashSet<>();
        objectParameters.add(objectParameter);
        objectParameters.add(objectParameter2);
        AdapterMethod adapterMethod = new AdapterMethod();
        adapterMethod.setParameters(objectParameters);
        adapterMethod.setExplain("测试");
        ObjectResponse objectResponse = new ObjectResponse();
        objectResponse.setGeneric("SingleResponse");
        objectResponse.setSubGeneric("List");
        objectResponse.setDataType("String");
        adapterMethod.setObjectResponse(objectResponse);
        adapterMethod.setName(methodName);
        adapterMethod.setUri("/" + methodName.toLowerCase());
        adapterMethod.setRequestType(RequestType.GET);
        return adapterMethod;
    }

    @Test
    public void generateRpc() {

        Rpc rpc = new Rpc();
        rpc.setAuthor("Vincent Vic");
        rpc.setDescription("Test");
        rpc.setClassName("ProjectAdapter");
        rpc.setPackageName("cn.meshed.cloud.rd.project");
        rpc.addImport("org.junit.Test");
        rpc.setVersion("1.0.0");
        rpc.setExplain("测试");
        ObjectParameter objectParameter = new ObjectParameter();
        objectParameter.setName("t1");
        objectParameter.setType("String");
        objectParameter.setExplain("111");
        objectParameter.setParameterType(ParameterType.NONE);
        ObjectParameter objectParameter2 = new ObjectParameter();
        objectParameter2.setName("t2");
        objectParameter2.setExplain("222");
        objectParameter2.setType("String");
        objectParameter2.setParameterType(ParameterType.NONE);
        Set<ObjectParameter> objectParameters = new HashSet<>();
        objectParameters.add(objectParameter);
        objectParameters.add(objectParameter2);
        AdapterMethod adapterMethod = new AdapterMethod();
        adapterMethod.setParameters(objectParameters);
        adapterMethod.setExplain("测试");
        ObjectResponse objectResponse = new ObjectResponse();
        objectResponse.setGeneric("SingleResponse");
        objectResponse.setSubGeneric("List");
        objectResponse.setDataType("String");
        adapterMethod.setObjectResponse(objectResponse);
        adapterMethod.setName("test");
        adapterMethod.setUri("/tt");
        rpc.setMethods(Collections.singleton(adapterMethod));
        String code = generateClassExecute.buildRpc(rpc);
        System.out.println(code);
    }
}