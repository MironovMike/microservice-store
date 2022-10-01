package ru.mironovmike.rates.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Rate {
    private final String code;
    private final double rate;
}
