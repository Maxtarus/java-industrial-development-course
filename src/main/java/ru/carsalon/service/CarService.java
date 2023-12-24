package ru.carsalon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.carsalon.entity.UserInfo;
import ru.carsalon.entity.car.Car;
import ru.carsalon.repository.CarRepository;
import ru.carsalon.security.AuthUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    @Transactional
    public List<Car> findAll() {
        List<Car> cars = repository.findAll();
        UserInfo user = AuthUtil.getUserFromContext();

        if (Objects.requireNonNull(user).isAdmin()) {
            return cars;
        } else if (user.getTrader() != null){
            return user.getTrader().getCars();
        } else {
            return new ArrayList<>();
        }
    }

    public Car save(Car car) {
        return repository.save(car);
    }
}
