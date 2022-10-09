package ru.mironovmike.store.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.mironovmike.store.entity.Product;
import ru.mironovmike.store.service.ProductService;

import java.util.Currency;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private final ProductService productService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("Run ContextRefreshedEvent handler");
        MonetaryAmount monetaryAmount = MonetaryAmount.builder().price(150d).currency(Currency.getInstance("RUB")).build();
        Product product = Product.builder()
                .title("Галоши")
                .packageAmount(6)
                .weight(1.5f)
                .monetaryAmount(monetaryAmount)
                .build();
        productService.create(product);
    }
}