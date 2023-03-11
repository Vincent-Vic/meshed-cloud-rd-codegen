package cn.meshed.cloud.rd.codegen.model;

import cn.meshed.cloud.utils.AssertUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * <h1>字段</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class JavaField implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 对象中文解释
     */
    private String explain;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段泛型
     */
    private String generic;

    /**
     * 字段注解（以及生成好的注解）
     */
    private Set<String> annotations;

    /**
     * 校验
     */
    public void verification() {
        //包名中含类名设施处理特殊处理
        AssertUtils.isTrue(StringUtils.isNotBlank(this.type),"类型不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(this.name),"名称不能为空");
    }
}
