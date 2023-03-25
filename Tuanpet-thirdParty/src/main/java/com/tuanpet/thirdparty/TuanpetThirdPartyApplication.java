package com.tuanpet.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TuanpetThirdPartyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuanpetThirdPartyApplication.class, args);
    }

}
