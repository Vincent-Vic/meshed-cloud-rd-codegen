package cn.meshed.cloud.rd.codegen.processor;

import cn.meshed.cloud.rd.codegen.Model;
import cn.meshed.cloud.rd.codegen.model.JavaModel;

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
}
