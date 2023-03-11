package cn.meshed.cloud.rd.codegen.model;

import cn.meshed.cloud.utils.AssertUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@Data
public class JavaMethod implements Comparable<JavaMethod>{

    /**
     * 对象中文解释
     */
    private String name;
    /**
     * 方法中文解释
     */
    private String explain;
    /**
     * 方法返回
     */
    private String response;

    /**
     * 注解
     */
    @Setter(AccessLevel.NONE)
    private Set<String> annotations;

    /**
     * 参数列表
     */
    @Setter(AccessLevel.NONE)
    private Set<JavaParameter> parameters;


    public void setAnnotations(Set<String> annotations) {
        if (CollectionUtils.isNotEmpty(annotations)){
            this.annotations = annotations.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }

    public void setParameters(Set<JavaParameter> parameters) {
        if (CollectionUtils.isNotEmpty(parameters)){
            this.parameters = parameters.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        }
    }

    /**
     * 校验
     */
    public void verification() {
        //包名中含类名设施处理特殊处理
        AssertUtils.isTrue(StringUtils.isNotBlank(this.name),"方法名称不能为空");
        AssertUtils.isTrue(StringUtils.isNotBlank(this.response),"返回不能为空");
    }

    /**
     * 等于0的时候表示相等；
     * 等于1的时候表示大于；
     * 等于-1的时候表示小于；
     */
    @Override
    public int compareTo(JavaMethod javaMethod) {
        int i = this.name.compareTo(javaMethod.name);
        if (i == 0){
            i = CollectionUtils.isNotEmpty(this.parameters) ? this.parameters.size() : 0;
            int target = CollectionUtils.isNotEmpty(javaMethod.parameters) ? javaMethod.parameters.size() : 0;
            if (i == target){
                return 0;
            } else if (i > target) {
                return 1;
            } else {
                return -1;
            }
        }
        return i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JavaMethod)) {
            return false;
        }
        JavaMethod that = (JavaMethod) o;
        int i = 0,j=0;
        if (CollectionUtils.isNotEmpty(getParameters())){
            i = getParameters().size();
        }
        if (CollectionUtils.isNotEmpty(that.getParameters())){
            j = that.getParameters().size();
        }
        //同名合同参数数量
        return getName().equals(that.getName()) && i == j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
