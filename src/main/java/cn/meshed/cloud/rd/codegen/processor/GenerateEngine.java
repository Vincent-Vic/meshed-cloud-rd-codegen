package cn.meshed.cloud.rd.codegen.processor;

import cn.meshed.cloud.rd.codegen.model.JavaDefinition;
import com.alibaba.cola.exception.SysException;

/**
 * <h1>生成引擎</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface GenerateEngine {

    /**
     * 生成代码
     *
     * @param templateName 模板名称
     * @param data         数据
     * @return 生成的代码
     */
    String generate(String templateName, JavaDefinition data) throws SysException;
}
