package ru.mironovmike.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mironovmike.store.entity.Product;
import ru.mironovmike.store.exception.NoSuchElementException;
import ru.mironovmike.store.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private final ProductRepository repository;

    @Override
    public Product create(Product product) {
        log.info("Saving product: " + product.getTitle());
        return repository.save(product);
    }

    @Override
    public Product findById(long id) {
        log.info("Find by id: " + id);
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
