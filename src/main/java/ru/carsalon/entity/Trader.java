package ru.carsalon.entity;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Trader {
    @Pattern(regexp = "^^([А-Я][а-я]+\\s){2}[А-Я][а-я]+$$", message = "Недопустимое значение ФИО!")
    private String name;

    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
    message = "Недопустимое значение email-а!")
    private String email;

    @Pattern(regexp = "^((8|\\+7|)[\\- ]?)?\\(?\\d{3,5}\\)?[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d(([\\- ]?\\d)?[\\- ]?\\d)?$",
            message = "Недопустимое значение номера телефона!")
    private String phoneNumber;
}
