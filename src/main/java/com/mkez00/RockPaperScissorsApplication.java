package com.mkez00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class RockPaperScissorsApplication {
	private static final Logger LOG = Logger.getLogger( RockPaperScissorsApplication.class.getName() );

	public static void main(String[] args) {
		SpringApplication.run(RockPaperScissorsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				LOG.info("addCorsMappings");
				registry.addMapping("/**");
			}
		};
	}
}
