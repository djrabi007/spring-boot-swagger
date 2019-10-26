package com.rabi.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
@Configuration
@EnableSwagger2
public class CourierConfig {
	
	/** Sentry- Error Tracking online -Start */
	 @Bean
	 public HandlerExceptionResolver sentryExceptionResolver() {
	        return new io.sentry.spring.SentryExceptionResolver();
	    }
	 /** Sentry- Error Tracking online -End */
	/* For Swagger --Start */
	@Bean
	public Docket postCourierApi() {
		
		return 
			new Docket(DocumentationType.SWAGGER_2)
			.groupName("Rabi world -Courier").apiInfo(apiInfo())
			.select().paths(regex("/processCourier.*")).build();
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Courier Service111")
				.description("Sample Rabi Document#####")
				.termsOfServiceUrl("www.google.com")
				.license("Rabi License")
				.licenseUrl("www.google.com")
				.version("1.0")
				.build();
	}

	/* For Swagger --End */
}
