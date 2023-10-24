package ru.carsalon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EngineType {
    PETROL("бензин"),
    DIESEL("дизель"),
    HYBRID("бензин-дизель"),
    ELECTRO("электро");

    @Getter
    private final String title;
}
