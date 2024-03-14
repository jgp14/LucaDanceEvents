package com.lucatic.grupo2.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EventsGrupo2Application {

	public static void main(String[] args) {
		SpringApplication.run(EventsGrupo2Application.class, args);
	}
}
