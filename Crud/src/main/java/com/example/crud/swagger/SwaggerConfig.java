package com.example.crud.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Student CRUD API")
                        .version("1.0")
                        .description("API Documentation for Student CRUD operations")
                        .contact(new Contact()
                                .name("Ankit")
                                .email("ankit.choudhary@gmail.com")));

    }
}
