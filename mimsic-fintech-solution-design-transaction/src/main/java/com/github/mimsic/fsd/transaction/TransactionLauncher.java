package com.github.mimsic.fsd.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.transaction"
})
public class TransactionLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(TransactionLauncher.class, args);
    }
}
