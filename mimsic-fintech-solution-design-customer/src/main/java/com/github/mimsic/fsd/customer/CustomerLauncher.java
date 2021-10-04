package com.github.mimsic.fsd.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
//        "com.github.mimsic.fsd.common.feign"
})
@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.customer"
})
public class CustomerLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(CustomerLauncher.class, args);
    }
}
