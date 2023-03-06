package cn.meshed.cloud.rd.codegen.factory;

import cn.meshed.cloud.rd.codegen.config.TemplateConfiguration;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class TemplateFactory {
    private final Configuration configuration;

    public TemplateFactory() {
        TemplateConfiguration templateConfiguration = new TemplateConfiguration();
        configuration = templateConfiguration.configuration();
    }

    public Template getTemplate(String template) throws IOException {
        return configuration.getTemplate(template);
    }
}
