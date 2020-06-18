package com.revature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author William Gentry
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
						.apiInfo(metaData())
						.select()
						.apis(RequestHandlerSelectors.basePackage("com.revature.todo"))
						.paths(PathSelectors.any())
						.build();
	}

	public ApiInfo metaData() {
		return new ApiInfo(
						"Todos API",
						"REST API for Revature Training Demonstrations",
						"1.0",
						"",
						new Contact(
										"Revature",
										"https://gitlab.revaturelabs.com/revprotodosapi/todos-api",
										"centerofexcellence@revature.com"
						),
						"",
						"",
						Collections.emptyList()
		);
	}
}
