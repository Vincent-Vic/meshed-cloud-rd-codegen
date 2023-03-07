package cn.meshed.cloud.rd.codegen.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@ConfigurationProperties(prefix = "code.generate")
@Data
public class GenerateProperties {

    private Map<String,String> packageMapping;

    private Map<String,Map<String,String>> annotationRule;
}
