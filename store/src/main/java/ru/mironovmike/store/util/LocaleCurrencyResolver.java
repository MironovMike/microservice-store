package ru.mironovmike.store.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class LocaleCurrencyResolver {
    public Currency getCurrency(Locale locale) {
        if (locale == null) {
            return Currency.getInstance("RUB");
        }
        Map<String, Currency> map = new HashMap<>();
        map.put(Locale.ENGLISH.getLanguage(), Currency.getInstance("USD"));
        return map.getOrDefault(locale.getLanguage(), Currency.getInstance("RUB"));
    }
}
