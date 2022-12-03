package ru.mironovmike.store.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mironovmike.store.entity.Rate;
import ru.mironovmike.store.service.CacheService;
import ru.mironovmike.store.util.KafkaMessage;

import java.util.function.Consumer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class KafkaConfig {
    @Autowired
    private final CacheService cacheService;

    @Bean
    public Consumer<KafkaMessage> input() {
        return message -> {
            log.info("Message consumed: " + message.toString());
            Rate rate = Rate.builder().code(message.getCurrency()).id(message.getCurrency()).build();
            cacheService.clearCache(rate);
        };
    }
}
