package com.denysenko.botuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class BotuserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotuserServiceApplication.class, args);
    }

}
