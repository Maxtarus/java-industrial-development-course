package ru.carsalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.carsalon.entity.RoleInfo;
import ru.carsalon.entity.RoleType;

import java.util.Optional;

@Repository
public interface RoleInfoRepository extends JpaRepository<RoleInfo, Long> {

    Optional<RoleInfo> findByName(RoleType role);
}