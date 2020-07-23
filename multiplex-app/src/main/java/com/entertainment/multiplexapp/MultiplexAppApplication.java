package com.entertainment.multiplexapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MultiplexAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplexAppApplication.class, args);
	}

}
