package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.AdapterMethod;
import cn.meshed.cloud.rd.codegen.ObjectDefinition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
     * 适配器方法列表
     */
    private List<Method> methods;
}
