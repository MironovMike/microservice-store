package ru.mironovmike.rates.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.mironovmike.rates.config.ServiceConfig;
import ru.mironovmike.rates.entity.Rate;
import ru.mironovmike.rates.exception.NoSuchRateException;
import ru.mironovmike.rates.utils.ActionEnum;
import ru.mironovmike.rates.utils.CallResult;
import ru.mironovmike.rates.utils.KafkaMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
    @Autowired
    private final StreamBridge streamBridge;

    @Autowired
    private final ServiceConfig config;

    @Override
    public Rate getRate(String code) throws TimeoutException {
        Map<String, Double> map = new HashMap<>();
        map.put("USD-RUB", 60.000d);
        Double rate = Optional.ofNullable(map.get(code))
                .orElseThrow(() -> new NoSuchRateException("No such code: " + code));
        if (config.isOutOfService()) {
            makeOutOfService();
        }
        return new Rate(code, rate);
    }

    @Override
    public CallResult processCurrencyAction(String currency, ActionEnum action) {
        this.postKafkaMessage(currency, action);
        String callResultMessage = String.format("Currency processed successfully: currency %s, action %s", currency, action);
        return CallResult.builder().message(callResultMessage).build();
    }

    private void postKafkaMessage(String currency, ActionEnum action) {
        log.info("Posting Kafka message...");
        KafkaMessage kafkaMessage = KafkaMessage.builder().action(action).currency(currency).build();
        Message<KafkaMessage> message = MessageBuilder.withPayload(kafkaMessage).build();
        streamBridge.send("output-out-0", message);
    }

    private void makeOutOfService() throws TimeoutException {
        try {
            Thread.sleep(2000);
            throw new TimeoutException("Can't get rate. Try later.");
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
