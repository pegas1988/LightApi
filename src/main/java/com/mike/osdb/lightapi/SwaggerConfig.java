package com.mike.osdb.lightapi;

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
                .info(
                        new Info()
                                .title("LightApi")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("pegas19881@gmail.com")
                                                .name("Briatko Mike")
                                )

                );
    }
}
