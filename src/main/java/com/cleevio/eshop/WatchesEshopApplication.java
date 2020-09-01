package com.cleevio.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan()
public class WatchesEshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchesEshopApplication.class, args);
	}
	
	/*
	@Bean
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/eshop/*"))
				.apis(RequestHandlerSelectors.basePackage("com"))
				.build();
	} */

}
