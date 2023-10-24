package ru.carsalon.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BodyType {
    SEDAN("седан"),
    UNIVERSAL("универсал"),
    HATCHBACK("хэтчбек"),
    COUPE("купе"),
    PICKUP("пикап"),
    VAN("фургон"),
    SUV("внедорожник"),
    CUV("кроссовер"),
    MINIVAN("минивэн"),
    CABRIOLET("кабриолет"),
    LIMOUSINE("лимузин"),
    MINIBUS("микроавтобус");

    @Getter
    private final String title;
}
