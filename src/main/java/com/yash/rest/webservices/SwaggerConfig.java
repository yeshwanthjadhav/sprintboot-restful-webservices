package com.yash.rest.webservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
	//http://localhost:8080/v2/api-docs
	//http://localhost:8080/swagger-ui.html
	
	  public static final Contact DEFAULT_CONTACT = new Contact("Yeshwanth", "https://javajadhav.blogspot.com", "yeshwanth.jadhav@gmail.com");

	  @SuppressWarnings("rawtypes")
	static Collection<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();
	  
	  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			  "title", 
			  "description", 
			  "version", 
			  "termsOfServiceUrl", 
			  DEFAULT_CONTACT, 
			  "license", 
			  "licenseUrl", 
			  vendorExtensions);

	private static final Set<String> DEFAULT_PRODUCERS_AND_CONSUMERS = 
			new HashSet<String>(Arrays.asList("applicaton/json", "applicaton/xml"));
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(DEFAULT_API_INFO)
        		.produces(DEFAULT_PRODUCERS_AND_CONSUMERS)
        		.consumes(DEFAULT_PRODUCERS_AND_CONSUMERS);
        
        		/*
        		.select()
        		.apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
                */
    }

} 