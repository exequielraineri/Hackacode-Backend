package com.hackacode.app.pack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan({"com.hackacode.commons.entity.models.entity"})
public class ServicioPackTuristicosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioPackTuristicosApplication.class, args);
    }

}
