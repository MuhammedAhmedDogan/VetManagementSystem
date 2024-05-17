package dev.patika.vet;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "vet", version = "1.0", description = "vet REST API with Spring Boot"))
public class VetApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetApplication.class, args);
	}

}