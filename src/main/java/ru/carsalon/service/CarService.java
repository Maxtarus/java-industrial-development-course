package ru.carsalon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.carsalon.entity.car.Car;
import ru.carsalon.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Car save(Car car) {
        return repository.save(car);
    }
}
