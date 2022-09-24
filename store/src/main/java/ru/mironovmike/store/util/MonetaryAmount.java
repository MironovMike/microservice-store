package ru.mironovmike.store.util;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Currency;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonetaryAmount {
    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "currency", length = 3)
    @NotNull
    private Currency currency;
}
