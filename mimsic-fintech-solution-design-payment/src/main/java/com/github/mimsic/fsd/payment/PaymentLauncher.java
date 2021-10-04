package com.github.mimsic.fsd.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.payment"
})
public class PaymentLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(PaymentLauncher.class, args);
    }
}
