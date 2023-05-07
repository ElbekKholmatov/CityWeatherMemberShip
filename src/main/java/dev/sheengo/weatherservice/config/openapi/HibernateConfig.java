package dev.sheengo.weatherservice.config.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class HibernateConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }


    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Weather Application")
                        .description("Get Weather information")
                        .version("1")
                        .contact(new Contact()
                                .name("Elbek Kholmatov")
                                .email("exolmatov99@gmail.com")
                                .url("https://github.com/ElbekKholmatov"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                        .termsOfService("http://swagger.io/terms/"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"))
                .servers(Arrays.asList(
//                        new Server().url("http://10.10.3.147:8080").description("Daxshatli Server"),
//                        new Server().url("http://localhost:8080").description("Production Server"),
                        new Server().url("http://localhost:8081").description("Main Server")
                )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components((new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                ));
    }

//    @Bean
//    public GroupedOpenApi authOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("auth")
//                .pathsToMatch("/api/v1/auth/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi allOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("all")
//                .pathsToMatch("/**")
//                .build();
//    }
}
