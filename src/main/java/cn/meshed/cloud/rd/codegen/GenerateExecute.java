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

    /**
     * 构建 Adapter 接口
     *
     * @param adapter 适配器数据
     * @return 代码
     */
    String buildAdapter(Adapter adapter);

    /**
     * 构建 RPC 接口
     *
     * @param rpc rpc数据
     * @return 代码
     */
    String buildRpc(Rpc rpc);
}
