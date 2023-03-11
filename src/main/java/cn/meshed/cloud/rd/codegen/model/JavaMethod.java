package cn.meshed.cloud.rd.codegen.model;

import cn.meshed.cloud.utils.AssertUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class JavaMethod {

    /**
     * 对象中文解释
     */
    private String name;
    /**
     * 方法中文解释
     */
    private String explain;
    /**
     * 方法返回
     */
    private String response;

    /**
     * 注解
     */
    private Set<String> annotations;

    /**
     * 参数列表
     */
    private Set<JavaParameter> parameters;

    /**
     * 校验
     */
    public void verification() {
        //包名中含类名设施处理特殊处理
        AssertUtils.isTrue(StringUtils.isNotBlank(this.name),"方法名称不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(this.response),"返回不能为空");
    }
}
