package com.eksad.miniproject.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class) // dari swagger
public class SwaggerConfig {

//docket : untuk inisialisasi swagger nya. swagger ingin membaca class apa, path apa
//apiInfo: akan memanggil metaInfo
	@Bean // kalo ga diinisialisasi dia ga akan membaca apa yg kita minta
	public Docket eksadAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.miniproject")) 
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo())
				.tags(
					new Tag("EnterpriseOrgStructure", "ES Management API"),
					new Tag("Person", "Person Management API"),
					new Tag("Data Manipulation API", ""),
					new Tag("Get Data API", "")
						)
				;	
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo (
				"ES Data Management REST API",  // judul API
				"Rest API Collection for ES Data Management", //deskripsi
				"1.0.0", 
				"http://google.com", 
				new Contact("Jingga Sella", "https://github.com/jinggasella", "jinggasella22@gmail.com"), //contact developer
				"Jingga 2.0", 
				"http://www.google.com/license", 
				Collections.emptyList()); 
		return apiInfo;
	}


}
