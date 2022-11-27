package ru.mironovmike.rates.service;

import ru.mironovmike.rates.entity.Rate;
import ru.mironovmike.rates.utils.ActionEnum;
import ru.mironovmike.rates.utils.CallResult;

import java.util.concurrent.TimeoutException;

public interface RateService {
    Rate getRate(String code) throws TimeoutException;
    CallResult processCurrencyAction(String currency, ActionEnum action);
}
