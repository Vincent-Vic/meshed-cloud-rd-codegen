package cn.meshed.cloud.rd.codegen.config;

import cn.meshed.cloud.rd.codegen.GenerateExecute;
import cn.meshed.cloud.rd.codegen.impl.GenerateExecuteImpl;
import cn.meshed.cloud.rd.codegen.processor.AnnotationProcessor;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
public class GenerateConfiguration {

    private final GenerateEngine generateEngine;
    private final AnnotationProcessor annotationProcessor;
    private final PackageProcessor packageProcessor;

    @Bean
    public GenerateExecute generateExecute(){
        return new GenerateExecuteImpl(generateEngine,packageProcessor,annotationProcessor);
    }
}
