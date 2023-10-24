package ru.carsalon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TransmissionType {
    AUTOMATIC("автоматическая"),
    VARIATOR("вариатор"),
    ROBOT("робот"),
    MANUAL("механическая");

    @Getter
    private final String title;
}
