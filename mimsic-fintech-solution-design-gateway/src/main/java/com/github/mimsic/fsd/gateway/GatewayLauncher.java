package com.github.mimsic.fsd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.gateway"
})
public class GatewayLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(GatewayLauncher.class, args);
    }
}
