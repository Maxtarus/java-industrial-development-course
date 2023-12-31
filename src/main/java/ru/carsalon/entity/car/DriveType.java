package ru.carsalon.entity.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DriveType {
    FRONT("передний"),
    REAR("задний"),
    FULL("полный");

    private final String title;
}
