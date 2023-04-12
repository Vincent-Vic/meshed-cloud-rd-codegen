package cn.meshed.cloud.rd.codegen.constant;

/**
 * <h1>通用常量区</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public interface Constant {

    /**
     * 无参数注解格式
     */
    String ANNOTATION_FORMAT = "@%s";

    /**
     * 带参数注解格式
     */
    String ANNOTATION_PARAMETER_FORMAT = "@%s(%s)";

    /**
     * 带参数注解格式
     */
    String ANNOTATION_SIMPLE_PARAMETER_FORMAT = "@%s(\"%s\")";

    /**
     * 带一个参数注解格式
     */
    String ANNOTATION_SINGLE_PARAMETER_FORMAT = "@%s(%s=%s)";

    /**
     * 泛型格式
     */
    String GENERIC_FORMAT = "%s<%s>";

    /**
     * 消息
     */
    String MESSAGE = "message";

    /**
     * 消息
     */
    String VALUE = "value";

    /**
     * 字符串
     */
    String STRING = "String";

    /**
     * 数字
     */
    String INTEGER = "Integer";

    /**
     * 适配器
     */
    String ADAPTER = "adapter";


    /**
     * RPC
     */
    String RPC = "rpc";

    /**
     * 模型
     */
    String MODEL = "model";

    /**
     * 枚举
     */
    String ENUM = "enum";



}
