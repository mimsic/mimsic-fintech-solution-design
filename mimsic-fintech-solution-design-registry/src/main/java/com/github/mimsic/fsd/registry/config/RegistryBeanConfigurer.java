package com.github.mimsic.fsd.registry.config;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaServer
public class RegistryBeanConfigurer {

    public RegistryBeanConfigurer() {
    }
}
