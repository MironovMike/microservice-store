package ru.mironovmike.store.util;

import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component

public class LocaleCurrencyResolver {
    public Currency getCurrency(Locale locale) {
        Map<Locale, Currency> map = new HashMap<>();
        map.put(Locale.US, Currency.getInstance("USD"));
        return map.getOrDefault(locale, Currency.getInstance("RUB"));
    }
}
