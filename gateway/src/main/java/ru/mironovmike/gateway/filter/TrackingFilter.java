package ru.mironovmike.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrackingFilter implements GlobalFilter {
    @Autowired
    private final FilterUtils filterUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if (isRequestIdPresents(headers)) {
            log.info(String.format("Found request ID: %s", filterUtils.getRequestId(headers)));

        } else {
            String requestId = generateRequestId();
            filterUtils.setRequestId(exchange, requestId);
            log.info(String.format("Generated request ID: %s", requestId));
        }
        return chain.filter(exchange);
    }

    private boolean isRequestIdPresents (HttpHeaders headers) {
        return filterUtils.getRequestId(headers) != null;
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}
