package br.com.programadorbr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(new Info()
                .title("RESTful API")
                .version("v1")
                .description("A RESTful API with Java 18 and Spring Boot 3")
                .termsOfService("https://www.programadorbr.com.br/")
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.programadorbr.com.br/")));
    }
}
