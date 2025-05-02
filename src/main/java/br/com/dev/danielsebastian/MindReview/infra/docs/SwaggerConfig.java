package br.com.dev.danielsebastian.MindReview.infra.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI(){
        Contact contact = new Contact();
        contact.setName("Daniel");
        contact.setEmail("daniel.sebastian.nc@gmail.com");

        Info info = new Info();
        info.title("MindReview");
        info.version("v1");
        info.description("Application to Review Questions");
        info.contact(contact);

        return new OpenAPI().info(info);
    }
}
