package com.bi.auth.authservice.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final long MAX_AGE_SEC = 3600L;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**")//
				.allowedOrigins("*")//
				.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")//
				.allowedHeaders("*")//
				.allowCredentials(true)//
				.maxAge(MAX_AGE_SEC);
	}

}
