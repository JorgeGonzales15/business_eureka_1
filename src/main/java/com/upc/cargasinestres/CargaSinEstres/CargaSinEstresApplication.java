package com.upc.cargasinestres.CargaSinEstres;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The CargaSinEstresApplication class is the main entry point for the Carga Sin Estres application.
 * It includes configuration for the application, initializes necessary beans, and inserts roles into the database.
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class CargaSinEstresApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200","https://cse-fundamentos.web.app", "http://localhost:8080") //aquí va el link de tu frontend desplegado
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Permitir los métodos necesarios
						.allowedHeaders("*"); // Permitir todos los encabezados
			}
		};
	}

	/**
	 * Configures and initializes the ModelMapper bean.
	 *
	 * @return A ModelMapper bean for mapping objects.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * The main method that starts the Carga Sin Estres application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CargaSinEstresApplication.class, args);
	}

} //http://localhost:8080/swagger-ui/index.html
