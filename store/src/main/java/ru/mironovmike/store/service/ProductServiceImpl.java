package ru.mironovmike.store.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mironovmike.store.entity.Product;
import ru.mironovmike.store.entity.Rate;
import ru.mironovmike.store.exception.NoSuchProductException;
import ru.mironovmike.store.exception.RateRequestException;
import ru.mironovmike.store.repository.ProductRepository;
import ru.mironovmike.store.util.LocaleCurrencyResolver;
import ru.mironovmike.store.util.MonetaryAmount;

import javax.validation.constraints.NotNull;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private final RestTemplate restTemplate;

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
    @CircuitBreaker(name = "rates")
    public Product findById(long id, Locale locale) {
        log.info(String.format("Find by Id: %s", id));
        Currency localeCurrency = localeCurrencyResolver.getCurrency(locale);
        Product product = repository
                .findById(id)
                .orElseThrow(()-> new NoSuchProductException(String.format("Product id %s not found", id)));
        @NotNull Currency productCurrency = product.getMonetaryAmount().getCurrency();
        if (!productCurrency.getCurrencyCode().equals(localeCurrency.getCurrencyCode())) {
            // Need convert RUB price to locale currency
            // Request rate to rates-service
            log.info("Rates service request...");
            ResponseEntity<Rate> responseRate = restTemplate.exchange("http://rates/v1/rate/{code}", HttpMethod.GET, null,
                    Rate.class, localeCurrency.getCurrencyCode() + "-RUB");

            // Get rate from body response
            Optional<Rate> optional = Optional.ofNullable(responseRate.getBody());
            Rate rate = optional.orElseThrow(() -> new RateRequestException("Rates service null response"));
            log.info(String.format("...got response rate: %s", rate.toString()));

            // Calculate price
            double price = product.getMonetaryAmount().getPrice() / rate.getRate();
            product.setMonetaryAmount(MonetaryAmount.builder()
                    .currency(Currency.getInstance(localeCurrency.getCurrencyCode()))
                    .price(price)
                    .build());
        }
        return product;
    }
}
