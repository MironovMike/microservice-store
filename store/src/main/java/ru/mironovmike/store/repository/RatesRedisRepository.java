package ru.mironovmike.store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mironovmike.store.entity.Rate;

@Repository
public interface RatesRedisRepository extends CrudRepository<Rate, String> {
}
