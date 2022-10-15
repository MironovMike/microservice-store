package ru.mironovmike.gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtils {
    public static final String REQUEST_ID = "request-id";

    public String getRequestId(HttpHeaders requestHeaders) {
        if (requestHeaders.containsKey(REQUEST_ID)) {
            List<String> requestIdList = requestHeaders.get(REQUEST_ID);
            if (requestIdList != null) {
                return requestIdList.stream().findFirst().orElse(null);
            }
        }
        return null;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(
                exchange.getRequest().mutate().header(name, value).build())
                .build();
    }

    public ServerWebExchange setRequestId(ServerWebExchange exchange, String requestId) {
        return setRequestHeader(exchange, REQUEST_ID, requestId);
    }
}
