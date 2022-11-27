package ru.mironovmike.store.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mironovmike.store.util.KafkaMessage;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class KafkaConfig {
    @Bean
    public Consumer<KafkaMessage> input() {
        return message -> {
            log.info("Message consumed: " + message.toString());
        };
    }
}
