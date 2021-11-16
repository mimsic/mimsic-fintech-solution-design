package com.github.mimsic.fsd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.gateway"
})
public class GatewayLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(GatewayLauncher.class, args);
    }
}
