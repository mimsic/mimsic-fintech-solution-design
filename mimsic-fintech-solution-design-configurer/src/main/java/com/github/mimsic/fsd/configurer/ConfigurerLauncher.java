package com.github.mimsic.fsd.configurer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ApplicationContext;

@EnableConfigServer
@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.configurer"
})
public class ConfigurerLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(ConfigurerLauncher.class, args);
    }
}
