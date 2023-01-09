package cplayer.favourites.app;

import org.springframework.boot.SpringApplication;    

//// Spring Boot Application which will be used for the purpose of User Authentication

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import cplayer.userauth.config.JWTFilter;

//configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning
//Spring boot application for the purpose of handling the favorites section
@SpringBootApplication
@ComponentScan({"cplayer.favourites.app.service","cplayer.favourites.app.repository","cplayer.favourites.app.controller"})
public class FavouritesApplication {
	
	// Bean of JWT token for validating the JWT provided in header
	@Bean
	public FilterRegistrationBean jwtFilter() {
		
		FilterRegistrationBean registrationbean = new FilterRegistrationBean();
		registrationbean.setFilter(new JWTFilter());
		registrationbean.addUrlPatterns("/api/*");
		return registrationbean;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(FavouritesApplication.class, args);
	}

}
