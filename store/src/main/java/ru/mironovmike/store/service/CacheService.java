package ru.mironovmike.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mironovmike.store.entity.Rate;
import ru.mironovmike.store.repository.RatesRedisRepository;

@Service
@RequiredArgsConstructor
public class CacheService {
    @Autowired
    private final RatesRedisRepository ratesRedisRepository;

    public Rate getById(String id) {
        return ratesRedisRepository.findById(id).orElse(null);
    }

    public Rate save(Rate rate) {
        return ratesRedisRepository.save(rate);
    }

    public void clearCache(Rate rate) {
        ratesRedisRepository.delete(rate);
    }
}
