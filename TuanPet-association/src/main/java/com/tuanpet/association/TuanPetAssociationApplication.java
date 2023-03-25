package com.tuanpet.association;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TuanPetAssociationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuanPetAssociationApplication.class, args);
    }

}
