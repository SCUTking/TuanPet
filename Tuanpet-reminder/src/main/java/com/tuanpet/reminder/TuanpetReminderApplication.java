package com.tuanpet.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TuanpetReminderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuanpetReminderApplication.class, args);
    }

}
