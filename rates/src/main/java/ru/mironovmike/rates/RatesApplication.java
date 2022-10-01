package ru.mironovmike.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class RatesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatesApplication.class, args);
    }

}
