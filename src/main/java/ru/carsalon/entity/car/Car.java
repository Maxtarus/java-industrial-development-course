package ru.carsalon.entity.car;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.carsalon.entity.Trader;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cars")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cars_GEN")
    @SequenceGenerator(name = "cars_GEN", sequenceName = "cars_SEQ", initialValue = 6, allocationSize = 20)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name="trader_id", nullable = false)
    private Trader trader;

    @NotBlank(message = "Поле \"Марка\" обязательно для заполнения!")
    @Column(name = "brand",  nullable = false)
    private String brand;

    @NotBlank(message = "Поле \"Модель\" обязательно для заполнения!")
    @Column(name = "model",  nullable = false)
    private String model;

    @Digits(integer = 4, fraction = 0, message = "Некорректный год!")
    @Min(value = 1990, message = "Год должен быть больше или равен 1990")
    @Max(value = 2023, message = "Год должен быть меньше или равен 2023")
    @Column(name = "release_year",  nullable = false)
    private int releaseYear;

    @Column(name = "body_type",  nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BodyType bodyType;

    @Column(name = "engine_type",  nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private EngineType engineType;

    @Column(name = "drive_type",  nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DriveType driveType;

    @Column(name = "transmission_type",  nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TransmissionType transmissionType;

    @NotBlank(message = "Поле \"Цвет\" обязательно для заполнения!")
    @Column(name = "color",  nullable = false)
    private String color;

    @Min(value = 0, message = "Пробег должен быть больше или равен 0 км")
    @Max(value = 1000000, message = "Пробег должен быть меньше или равен 1 000 000 км")
    @Column(name = "mileage",  nullable = false)
    private int mileage;

    @Min(value = 10000, message = "Цена должна быть больше или равна 10 000 руб")
    @Max(value = 100000000, message = "Цена должна быть меньше или равна 100 000 000 руб")
    @Column(name = "price",  nullable = false)
    private int price;
}
