package cn.meshed.cloud.rd.codegen.config;

import cn.meshed.cloud.rd.codegen.GenerateClassExecute;
import cn.meshed.cloud.rd.codegen.impl.GenerateClassExecuteImpl;
import cn.meshed.cloud.rd.codegen.processor.AnnotationProcessor;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * <h1>构建执行器自动配置</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
public class GenerateAutoConfiguration {

    private final GenerateEngine generateEngine;
    private final AnnotationProcessor annotationProcessor;
    private final PackageProcessor packageProcessor;

    @Bean
    public GenerateClassExecute generateExecute(){
        return new GenerateClassExecuteImpl(generateEngine,packageProcessor,annotationProcessor);
    }
}
