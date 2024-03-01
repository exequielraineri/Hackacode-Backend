package com.hackacode.app.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan({"com.hackacode.commons.entity.models.entity"})
public class ServicioClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioClientesApplication.class, args);
	}

}
