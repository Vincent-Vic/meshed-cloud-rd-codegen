package ${packageName};

<#if imports??>
    <#list imports as import>
import ${import};
    </#list>
</#if>


/**
* <h1>${explain!className}</h1>
* <p>${description}</p>
*
* @author ${author}
* @version ${version}
*/
public interface ${className} <#if superClass??>extends ${superClass} </#if>{

<#if methods??>
    <#list methods as method>
    /**
    * <h2>${method.explain!method.name}</h2>
    *
<#list method.parameters as parameter>
    * @param ${parameter.name} ${parameter.explain!parameter.name}
</#list>
    * @return {@link ${method.response}}
    */
<#if method.annotations??>
    <#list method.annotations as annotation>
    ${annotation}
    </#list>
</#if>
    ${method.response} ${method.name}(<#assign index = 0><#list method.parameters as parameter><#if index != 0>, </#if><#assign index = index+1><#if parameter.annotations??><#list parameter.annotations as annotation>${annotation} </#list></#if>${parameter.type} ${parameter.name}</#list>);
    </#list>
</#if>

}
