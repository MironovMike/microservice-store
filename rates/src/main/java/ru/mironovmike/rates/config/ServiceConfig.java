package ru.mironovmike.rates.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rates")
@Getter
@Setter
public class ServiceConfig {
    private boolean outOfService;
}