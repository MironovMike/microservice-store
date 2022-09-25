package ru.mironovmike.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.mironovmike.store.entity.Product;
import ru.mironovmike.store.exception.NoSuchElementException;
import ru.mironovmike.store.repository.ProductRepository;
import ru.mironovmike.store.util.LocaleCurrencyResolver;

import javax.validation.constraints.NotNull;
import java.util.Currency;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private final ProductRepository repository;

    @Autowired
    private final LocaleCurrencyResolver localeCurrencyResolver;

    @Qualifier("messageSource")
    @Autowired
    MessageSource messages;

    @Override
    public Product create(Product product) {
        log.info("Saving product: " + product.getTitle());
        return repository.save(product);
    }

    @Override
    public Product findById(long id, Locale locale) {
        log.info("Find by Id: " + id);
        Currency currency = localeCurrencyResolver.getCurrency(locale);
        Product product = repository
                .findById(id)
                .orElseThrow(()-> new NoSuchElementException(String.format("Product id %s not found", id)));
        @NotNull Currency productCurrency = product.getMonetaryAmount().getCurrency();
        if (!productCurrency.getCurrencyCode().equals(currency.getCurrencyCode())) {
            // TODO Request to exchange rate service to perform currency maths
        }
        return product;
    }
}
