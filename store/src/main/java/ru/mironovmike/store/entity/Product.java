package ru.mironovmike.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import ru.mironovmike.store.util.MonetaryAmount;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "package_amount")
    @NotNull
    private Integer packageAmount;

    @Column(name = "weight")
    @NotNull
    private float weight;

    @Formula("(select (p.weight * p.package_amount) from product p where p.id = id)")
    private float packageWeight;

    @Embedded
    private MonetaryAmount monetaryAmount;
}
