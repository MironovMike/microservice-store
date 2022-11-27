package ru.mironovmike.rates.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mironovmike.rates.utils.ActionEnum;
import ru.mironovmike.rates.utils.KafkaMessage;

import java.util.function.Supplier;

@Configuration
@Slf4j
public class KafkaConfig {
//    @Bean
//    public Supplier<KafkaMessage> output() {
//        return () -> KafkaMessage.builder().action(ActionEnum.UPDATE).currency("USD-RUB").build();
//    }
}
