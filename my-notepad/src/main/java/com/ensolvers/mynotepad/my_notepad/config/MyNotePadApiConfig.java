package com.ensolvers.mynotepad.my_notepad.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyNotePadApiConfig {

    @Bean
    public OpenAPI springAccommodationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("My NotePad management API")
                        .description("API that provides services for the management, administration and control of the notes and categories by My NotePad")
                        .version("v0.0.1"));
    }
}
