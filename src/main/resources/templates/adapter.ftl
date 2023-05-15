package ${packageName};

import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import javax.validation.Valid;

<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>


/**
* <h1>${explain!className}</h1>
* <p>${description}</p>
*
* @author ${author!'Meshed Cloud RD'}
* @version ${version!'1.0.0'}
*/
@RequestMapping("${uri!'/'+className}")
public interface ${className} extends Serializable {

    long serialVersionUID = 1L;

<#if methods??>
    <#list methods as method>
    /**
    * <h2>${method.explain!method.name}</h2>
    * ${method.description!''}
<#if method.parameters??><#list method.parameters as parameter>
    * @param ${parameter.name} ${parameter.explain!parameter.name}
</#list></#if>
    * @return {@link ${method.response}}
    */
    @Operation(summary = "${method.explain!method.name}")
<#if method.annotations??>
    <#list method.annotations as annotation>
    ${annotation}
    </#list>
</#if>
    ${method.response} ${method.name}(<#if method.parameters??><#assign index = 0><#list method.parameters as parameter><#if index != 0>, </#if><#assign index = index+1>@Parameter(description = "${parameter.explain!parameter.name}") @Valid <#if parameter.annotations??><#list parameter.annotations as annotation>${annotation} </#list></#if>${parameter.type} ${parameter.name}</#list></#if>);
    </#list>
</#if>

}
