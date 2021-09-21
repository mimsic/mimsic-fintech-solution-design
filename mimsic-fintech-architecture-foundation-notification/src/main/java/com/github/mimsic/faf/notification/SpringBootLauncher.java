package com.github.mimsic.faf.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.github.mimsic.faf.notification"
})
public class SpringBootLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringBootLauncher.class, args);
    }
}
