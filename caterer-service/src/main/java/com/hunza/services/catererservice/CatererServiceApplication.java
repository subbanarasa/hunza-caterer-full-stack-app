package com.hunza.services.catererservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CatererServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatererServiceApplication.class, args);
	}

}
