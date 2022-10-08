package ru.mironovmike.rates.service;

import ru.mironovmike.rates.entity.Rate;

import java.util.concurrent.TimeoutException;

public interface RateService {
    Rate getRate(String code) throws TimeoutException;
}
