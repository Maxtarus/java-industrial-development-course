package ru.carsalon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.carsalon.entity.Trader;
import ru.carsalon.entity.car.Car;
import ru.carsalon.repository.CarRepository;
import ru.carsalon.repository.TraderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraderService {
    private final TraderRepository repository;
    private final CarService carService;

    public List<Trader> findAll() {
        return repository.findAll();
    }

    public Trader save(Trader trader) {
        return repository.save(trader);

    }
}
