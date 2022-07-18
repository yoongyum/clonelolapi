package com.clonelol;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ClonelolapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClonelolapiApplication.class, args);
	}

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Bean
	public Gson getGson() {
		return new Gson();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return restTemplateBuilder.build();
	}
}
