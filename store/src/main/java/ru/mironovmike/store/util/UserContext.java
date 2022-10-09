package ru.mironovmike.store.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserContext {
    public static final String REQUEST_ID = "request-id";
    private String requestId;
}
