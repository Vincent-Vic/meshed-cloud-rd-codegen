package ${packageName};

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>

/**
* <h1>${explain!className}</h1>
* <p>${description!''}</p>
*
* @author ${author!'Meshed Cloud RD'}
* @version ${version!'1.0.0'}
*/
@EqualsAndHashCode(callSuper = false)
@Data
@ApiModel(value="${explain!className}",description="${description!className}")
public class ${className} extends ${superClass} {

    private static final long serialVersionUID = 1L;

<#if fields??>
    <#list fields as field>
    /**
    * ${field.explain}
    */
    <#list field.annotations as annotation>
    ${annotation}
    </#list>
    private <#if field.generic??>${field.generic}${r'<'}</#if>${field.type}<#if field.generic??>${r'>'}</#if> ${field.name};
    </#list>
</#if>

}