package br.com.zupacademy.felipe.gadelha.proposta.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Meli API")
						.description("API que simula uma aplicação de Proposta")
						.version("v1.0")
						.contact(new Contact().name("Felipe Gadelha Diniz Da Silva")
								.url("https://www.linkedin.com/in/felipe-gadelha-diniz-da-silva-aaaa4a158/")
								.email("felipegadelha90@gmail.com")))
				.externalDocs(new ExternalDocumentation().description("Proposal API Github Documentation")
						.url("https://github.com/FelipeGadelha/orange-talents-06-template-proposta"));
	}
}
