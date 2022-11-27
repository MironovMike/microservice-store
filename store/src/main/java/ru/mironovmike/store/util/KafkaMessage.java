package ru.mironovmike.store.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaMessage {
    private final ActionEnum action;
    private final String currency;
}
