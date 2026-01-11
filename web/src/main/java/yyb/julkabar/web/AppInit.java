package yyb.julkabar.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "yyb.julkabar")
@EnableJpaRepositories(basePackages = "yyb.julkabar.persistence.repo")
@EntityScan(basePackages = "yyb.julkabar.core.domain")
@ComponentScan(basePackages = "yyb.julkabar")
public class AppInit {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AppInit.class, args);
    }
}
