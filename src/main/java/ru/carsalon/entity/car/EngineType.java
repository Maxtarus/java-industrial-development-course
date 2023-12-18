package ru.carsalon.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EngineType {
    PETROL("бензин"),
    DIESEL("дизель"),
    HYBRID("бензин-дизель"),
    ELECTRO("электро");

    private final String title;
}
