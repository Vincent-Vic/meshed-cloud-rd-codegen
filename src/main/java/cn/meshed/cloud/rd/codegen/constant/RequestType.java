package cn.meshed.cloud.rd.codegen.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * <h1>请求类型枚举</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@AllArgsConstructor
@Getter
@ToString
public enum RequestType {

    /**
     * Get类型
     */
    GET("GetMapping","org.springframework.web.bind.annotation.GetMapping"),
    /**
     * Post类型
     */
    POST("PostMapping","org.springframework.web.bind.annotation.PostMapping"),
    /**
     * Put类型
     */
    PUT("PutMapping","org.springframework.web.bind.annotation.PutMapping"),
    /**
     * Delete类型
     */
    DELETE("DeleteMapping","org.springframework.web.bind.annotation.DeleteMapping"),
    /**
     * Patch类型
     */
    PATCH("PatchMapping","org.springframework.web.bind.annotation.PatchMapping"),
    ;

    private final String mapping;
    private final String importName;
}
