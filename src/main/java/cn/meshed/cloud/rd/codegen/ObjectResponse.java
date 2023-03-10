package cn.meshed.cloud.rd.codegen;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static cn.meshed.cloud.rd.codegen.constant.Constant.GENERIC_FORMAT;

/**
 * <h1>返回类型</h1>
 * 都不存在 默认void
 * @author Vincent Vic
 * @version 1.0
 */
@Getter
@Setter
public class ObjectResponse {

    /**
     * 返回数据类型
     */
    private String dataType;

    /**
     * 主要泛型
     */
    private String generic;

    /**
     * 第二泛型
     */
    private String subGeneric;

    @Override
    public String toString() {
        if (StringUtils.isBlank(dataType) && StringUtils.isBlank(generic) && StringUtils.isBlank(subGeneric)){
            return "void";
        }
        String type = null;
        if (StringUtils.isNotBlank(dataType)){
            type = dataType;
        }
        //数据类型存在和泛型同时存在
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(subGeneric)){
            type = String.format(GENERIC_FORMAT,subGeneric,type);
        } else if (StringUtils.isNotBlank(subGeneric)){
            // 没有数据类型，仅有第二泛型
            type = subGeneric;
        }
        //前面封装类型存在和第一泛型存在
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(generic)){
            type = String.format(GENERIC_FORMAT,generic,type);
        } else {
            // 没有数据类型，仅有第一泛型
            type = generic;
        }
        return type;
    }
}
