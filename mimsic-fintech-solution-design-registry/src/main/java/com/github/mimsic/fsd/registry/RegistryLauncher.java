package com.github.mimsic.fsd.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ApplicationContext;

@EnableEurekaServer
@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.registry"
})
public class RegistryLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(RegistryLauncher.class, args);
    }
}
