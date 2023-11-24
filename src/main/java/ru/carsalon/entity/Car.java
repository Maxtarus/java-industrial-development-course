package ru.carsalon.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import ru.carsalon.entity.enums.BodyType;
import ru.carsalon.entity.enums.DriveType;
import ru.carsalon.entity.enums.EngineType;
import ru.carsalon.entity.enums.TransmissionType;
import lombok.Data;

@Data
public class Car {
    @NotBlank(message = "Поле \"Марка\" обязательно для заполнения!")
    private String brand;

    @NotBlank(message = "Поле \"Модель\" обязательно для заполнения!")
    private String model;

    @Pattern(regexp = "^(19\\d{2}|20[01]\\d|202[0-3])$", message = "Некорректный год!")
    private String releaseYear;

    private BodyType bodyType;
    private EngineType engineType;
    private DriveType driveType;
    private TransmissionType transmissionType;

    @NotBlank(message = "Поле \"Цвет\" обязательно для заполнения!")
    private String color;

    @Pattern(regexp = "^([1-9]\\d{4,8})$", message = "Недопустимое значение пробега!")
    private String mileage;

    @Pattern(regexp = "^([1-9]\\d{4,8})$", message = "Недопустимое значение цены!")
    private String price;
}
