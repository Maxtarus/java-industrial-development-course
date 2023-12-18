package ru.carsalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.carsalon.entity.car.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> { }
