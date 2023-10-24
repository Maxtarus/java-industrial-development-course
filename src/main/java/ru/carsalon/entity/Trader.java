package ru.carsalon.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Trader {
    @NotBlank(message = "Поле \"ФИО\" обязательно для заполнения!")
    private String name;
    @Pattern(regexp= "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
    message = "Недопустимое значение email-а!")
    private String email;
    @Pattern(regexp = "^(8\\d{10})$", message = "Недопустимое значение номер телефона!")
    private String phoneNumber;
}
