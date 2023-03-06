package cn.meshed.cloud.rd.codegen;

/**
 * <h1>生成执行器</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface GenerateExecute {

    /**
     * 构建model
     *
     * @param model 模型数据
     * @return 代码
     */
    String buildModel(Model model);
}
