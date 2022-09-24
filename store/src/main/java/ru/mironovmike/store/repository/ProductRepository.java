package ru.mironovmike.store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mironovmike.store.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
