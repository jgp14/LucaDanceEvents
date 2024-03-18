package com.lucatic.grupo2.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Clase principal
 *
 * @author BlueDevTeam
 * @version 1.0.0
 * @since 15-03-2024
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EventsGrupo2Application {

	/**
	 * Punto de entrada al programa principal
	 * @param args ARgumentos por línea de comandos
	 */
	public static void main(String[] args) {
		SpringApplication.run(EventsGrupo2Application.class, args);
	}
}
