package com.hackacode.app.servicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan({"com.hackacode.commons.entity.models.entity"})
public class ServiciosServiciosTuristicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosServiciosTuristicosApplication.class, args);
	}

}
