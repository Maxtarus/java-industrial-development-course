package ru.carsalon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import ru.carsalon.entity.car.Car;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="traders")
@Entity
public class Trader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "traders_GEN")
    @SequenceGenerator(name = "traders_GEN", sequenceName = "traders_SEQ", initialValue = 6, allocationSize = 20)
    private long id;

    @Pattern(regexp = "^([А-Я][а-я]+\\s){2}[А-Я][а-я]+$", message = "Недопустимое значение ФИО!")
    @Column(name = "name", nullable = false)
    private String name;

    @Email(message = "Недопустимое значение email-а!")
    @Column(name = "email",  nullable = false)
    private String email;

    @Pattern(regexp = "^((8|\\+7|)[\\- ]?)?\\(?\\d{3,5}\\)?[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d[\\- ]?\\d(([\\- ]?\\d)?[\\- ]?\\d)?$",
            message = "Недопустимое значение номера телефона!")
    @Column(name = "phone_number",  nullable = false)
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trader")
    private List<Car> cars = new ArrayList<>();
}
