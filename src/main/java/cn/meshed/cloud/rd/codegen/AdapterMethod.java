package cn.meshed.cloud.rd.codegen;

import cn.meshed.cloud.rd.codegen.constant.RequestType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

import static cn.meshed.cloud.rd.codegen.constant.Constant.ANNOTATION_FORMAT;
import static cn.meshed.cloud.rd.codegen.constant.Constant.ANNOTATION_SIMPLE_PARAMETER_FORMAT;

/**
 * <h1>适配器方法</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AdapterMethod extends ObjectMethod {

    /**
     * uri
     */
    private String uri;

    /**
     * 请求类型
     */
    private RequestType requestType;

    /**
     * 注解
     */
    @Override
    public Set<String> getAnnotations() {
        if (this.requestType != null) {
            String requestMapping = this.requestType.getMapping();
            if (StringUtils.isNotBlank(this.uri)) {
                super.addAnnotation(String.format(ANNOTATION_SIMPLE_PARAMETER_FORMAT, requestMapping, this.uri));
            } else {
                super.addAnnotation(String.format(ANNOTATION_FORMAT, requestMapping));
            }
        }
        return super.getAnnotations();
    }

}
