package ru.mironovmike.store.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.mironovmike.store.entity.Product;
import ru.mironovmike.store.service.ProductService;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationStartingEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private final ProductService productService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Run ContextRefreshedEvent handler");
        Product product = Product.builder()
                .title("Галоши")
                .packageAmount(6)
                .weight(1.5f)
                .build();
        productService.create(product);
        // @Formula executes only on SELECT
        product = productService.findById(product.getId());
        log.info(product.getTitle() + " package weight: " + product.getPackageWeight());
    }
}