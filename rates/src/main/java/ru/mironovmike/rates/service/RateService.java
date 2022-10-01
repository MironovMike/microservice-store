package ru.mironovmike.rates.service;

import ru.mironovmike.rates.entity.Rate;

public interface RateService {
    Rate getRate(String code);
}
