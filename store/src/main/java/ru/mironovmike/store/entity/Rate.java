package ru.mironovmike.store.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Data
@RedisHash("rate")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    private String id;
    private String code;
    private double rate;
}
