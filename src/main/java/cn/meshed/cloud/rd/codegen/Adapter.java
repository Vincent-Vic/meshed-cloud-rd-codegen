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
public class Adapter extends ObjectDefinition {

    /**
     * URI
     */
    private String uri;

    /**
     * 适配器方法列表
     */
    private Set<AdapterMethod> methods;
}
