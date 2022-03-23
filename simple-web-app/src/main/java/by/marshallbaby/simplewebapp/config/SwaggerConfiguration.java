package by.marshallbaby.simplewebapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI swaggerCustomConfiguration() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Simple Web App API")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Alexey Yatsevich")
                                                .url("https://github.com/MarshallBaby")
                                )
                );
    }
}