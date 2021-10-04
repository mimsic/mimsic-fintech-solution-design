package com.github.mimsic.fsd.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = {
        "com.github.mimsic.fsd.notification"
})
public class NotificationLauncher {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(NotificationLauncher.class, args);
    }
}
