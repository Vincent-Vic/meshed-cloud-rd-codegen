package cn.meshed.cloud.rd.codegen.utils;

import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class JsonUtils {

    public static <T> Map<String,Object> toMapStringObject(T data){
        String jsonStr = JSONUtil.toJsonStr(data);
        return JSONUtil.toBean(jsonStr, Map.class);
    }
}
