package ru.mironovmike.store.service;

import ru.mironovmike.store.entity.Product;

import java.util.Locale;

public interface ProductService {
    Product create(Product product);
    Product findById(long id, Locale locale);
}
