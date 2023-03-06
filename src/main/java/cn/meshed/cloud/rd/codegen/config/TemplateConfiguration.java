package cn.meshed.cloud.rd.codegen.config;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <h1></h1>
 *
 * @author Vincent Vic
 * @version 1.0
 */
public class TemplateConfiguration {

    public Configuration configuration(){
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        try {
            configuration.setDirectoryForTemplateLoading(getTemplatePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public File getTemplatePath(){
        URL templates = ClassLoader.getSystemResource("templates");
        if (templates == null || templates.getFile() == null){
            throw new RuntimeException("模板资源不存在");
        }
        return new File(templates.getFile());
    }
}
