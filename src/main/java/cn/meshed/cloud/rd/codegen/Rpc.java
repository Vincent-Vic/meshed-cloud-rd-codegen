package cn.meshed.cloud.rd.codegen;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * <h1>适配器数据</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class Rpc extends ObjectDefinition {

    /**
     * 适配器方法列表 取保方法名称和参数组合不重复
     */
    private Set<ObjectMethod> methods;
}
