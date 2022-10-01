package ru.mironovmike.rates.service;

import org.springframework.stereotype.Service;
import ru.mironovmike.rates.entity.Rate;
import ru.mironovmike.rates.exception.NoSuchRateException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {
    @Override
    public Rate getRate(String code) {
        Map<String, Double> map = new HashMap<>();
        map.put("USD-RUB", 60.000d);
        Double rate = Optional.ofNullable(map.get(code))
                .orElseThrow(() -> new NoSuchRateException("No such code: " + code));
        return new Rate(code, rate);
    }
}
