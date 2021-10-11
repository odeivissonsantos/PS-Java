package br.com.supera.gamestore;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioJavaGameStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaGameStoreApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
			.info(
				new Info()
					.title("API - Desafio Game Store")
					.description("Api de desafio Java, para um E-commerce de jogos mobile")
					.contact(new Contact()
						.name("Deivisson Santos")
						.email("deivissonsantos@hotmail.com")
					)
					.version("V1")
					.license(new License()
						.name("Apache 2.0")
					)
			);
	}

}
