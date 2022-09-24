package ru.mironovmike.store.service;

import ru.mironovmike.store.entity.Product;

public interface ProductService {
    Product create(Product product);
    Product findById(long id);
}
