package com.github.mimsic.fsd.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.product"
})
public class ProductLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(ProductLauncher.class, args);
    }
}
