package ru.carsalon.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransmissionType {
    AUTOMATIC("автоматическая"),
    VARIATOR("вариатор"),
    ROBOT("робот"),
    MANUAL("механическая");

    private final String title;
}
