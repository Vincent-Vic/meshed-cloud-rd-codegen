package cn.meshed.cloud.rd.codegen.config;

import cn.meshed.cloud.rd.codegen.factory.TemplateFactory;
import cn.meshed.cloud.rd.codegen.processor.AnnotationProcessor;
import cn.meshed.cloud.rd.codegen.processor.GenerateEngine;
import cn.meshed.cloud.rd.codegen.processor.PackageProcessor;
import cn.meshed.cloud.rd.codegen.processor.impl.AnnotationProcessorImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.GenerateEngineImpl;
import cn.meshed.cloud.rd.codegen.processor.impl.PackageProcessorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * <h1>处理器注册Bean</h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
@RequiredArgsConstructor
public class ProcessorConfiguration {

    private final TemplateFactory templateFactory;
    private final GenerateProperties generateProperties;

    @Bean
    public GenerateEngine generateEngine(){
        return new GenerateEngineImpl(templateFactory);
    }
    @Bean
    public AnnotationProcessor annotationProcessor(){
        return new AnnotationProcessorImpl(generateProperties);
    }
    @Bean
    public PackageProcessor packageProcessor(){
        return new PackageProcessorImpl(generateProperties);
    }
}
