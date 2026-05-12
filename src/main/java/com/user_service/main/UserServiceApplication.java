package com.user_service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.user_service")
@EnableJpaRepositories(basePackages = "com.user_service.repository")
@EntityScan(basePackages = "com.user_service.entity")
@EnableFeignClients
public class UserServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                UserServiceApplication.class,
                args
        );
    }
}