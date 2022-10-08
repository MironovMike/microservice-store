package ru.mironovmike.rates.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mironovmike.rates.config.ServiceConfig;
import ru.mironovmike.rates.entity.Rate;
import ru.mironovmike.rates.exception.NoSuchRateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {
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

    private void makeOutOfService() throws TimeoutException {
        try {
            Thread.sleep(2000);
            throw new TimeoutException();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
