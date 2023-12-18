package ru.carsalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.carsalon.entity.Trader;

@Repository
public interface TraderRepository extends JpaRepository<Trader, Long> { }
