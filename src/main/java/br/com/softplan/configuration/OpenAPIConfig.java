package br.com.softplan.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI createOpenApi() {
        Contact contact = new Contact()
                .email("raylson11@gmail.com")
                .name("Raylson Novais")
                .url("wwww");

        Info info = new Info()
                .title("Revizia API")
                .version("1.0")
                .contact(contact)
                .description("API para controle de contas correntes");

        return new OpenAPI().info(info);
    }
}
