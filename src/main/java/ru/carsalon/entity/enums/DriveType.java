package ru.carsalon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DriveType {
    FRONT("передний"),
    REAR("задний"),
    FULL("полный");

    @Getter
    private final String title;
}
