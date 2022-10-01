package ru.mironovmike.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mironovmike.store.entity.Product;
import ru.mironovmike.store.service.ProductService;

import java.util.Locale;

@RestController
@RequestMapping(value = "v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    @Autowired
    private final ProductService productService;

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("productId") long productId,
                                              @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(productService.findById(productId, locale));
    }
}
