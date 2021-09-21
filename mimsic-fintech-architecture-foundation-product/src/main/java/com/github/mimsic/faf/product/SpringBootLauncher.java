package com.github.mimsic.faf.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.github.mimsic.faf.product"
})
public class SpringBootLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(SpringBootLauncher.class, args);
    }
}
