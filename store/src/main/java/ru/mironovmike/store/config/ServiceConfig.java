package ru.mironovmike.store.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ServiceConfig {
    @Value("${redis.server}")
    private String redisServer;

    @Value("${redis.port}")
    private String redisPort;
}
