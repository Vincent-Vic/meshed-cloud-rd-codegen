package cn.meshed.cloud.rd.codegen.processor;

import cn.meshed.cloud.rd.codegen.AdapterMethod;
import cn.meshed.cloud.rd.codegen.Method;
import cn.meshed.cloud.rd.codegen.model.JavaInterface;
import cn.meshed.cloud.rd.codegen.model.JavaModel;

import java.util.List;
import java.util.Set;

/**
 * <h1>包处理器 - 提取依赖包列表</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface PackageProcessor {

    /**
     * 通过java对象类型获取导入的包
     *
     * @param objectType 类型
     * @return 导入的包
     */
    String getBasePackage(String objectType);

    /**
     * 扫描模型包
     *
     * @param javaModel 模型
     * @return 全部所需包列表
     */
    Set<String> scanModelPackage(JavaModel javaModel);


    /**
     * 扫描Java接口维度包
     * @param javaInterface 接口
     * @return 接口维度所需包列表
     */
    Set<String> scanJavaInterfacePackage(JavaInterface javaInterface);

    /**
     * 扫描方法中的特定的依赖包
     *
     * @param methods 方法列表
     * @return 业务所需包列表
     */
    Set<String> scanMethodPackage(List<Method> methods);

    /**
     * 扫描方法中的特定的依赖包
     *
     * @param methods 方法列表
     * @return 业务所需包列表
     */
    Set<String> scanAdapterMethodPackage(List<AdapterMethod> methods);

}
