package com.github.mimsic.fsd.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
        "com.github.mimsic.fsd.api.feign.customer"
})
@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.order"
})
public class OrderLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(OrderLauncher.class, args);
    }
}

