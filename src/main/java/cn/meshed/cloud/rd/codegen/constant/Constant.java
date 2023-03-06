package cn.meshed.cloud.rd.codegen.constant;

/**
 * <h1></h1>
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
     * 带一个参数注解格式
     */
    String ANNOTATION_SINGLE_PARAMETER_FORMAT = "@%s(%s=%s)";

    /**
     * 参数格式
     */
    String PARAMETER_FORMAT = "%s=%s";

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

}
