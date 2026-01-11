package yyb.julkabar.web;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class EmailFreemarkerConfig {

    @Bean
    public Configuration emailFreemarkerConfiguration() {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        cfg.setDefaultEncoding("UTF-8");
        cfg.setClassLoaderForTemplateLoading(
                getClass().getClassLoader(),
                "mail-templates"
        );

        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return cfg;
    }
}
