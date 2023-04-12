package ${packageName};

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
* <h1>${explain!className}</h1>
* <p>${description!''}</p>
*
* @author ${author!'Meshed Cloud RD'}
* @version ${version!'1.0.0'}
*/
@AllArgsConstructor
@Getter
@ToString
public enum ${className} {

<#if enumValues??>
    <#list enumValues as enumValue>
        /**
        * ${enumValue.explain}
        */
        ${enumValue.name}(${enumValue.value}, "${enumValue.ext}"),
    </#list>
</#if>
        ;

        /**
        * 存储值
        */
        @EnumValue
        private final int value;
        /**
        * 扩展
        */
        private final String ext;

}