package com.tuanpet.activitythought;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TuanPetActivityThoughtApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuanPetActivityThoughtApplication.class, args);
    }

}
