package ru.carsalon.configuration;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.carsalon.entity.car.*;
import ru.carsalon.repository.CarRepository;
import ru.carsalon.repository.TraderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationInitializer implements CommandLineRunner {
    private final TraderRepository traderRepository;
//    private final CarRepository carRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void run(String... args) {
        for (Car car : loadTradersCars()) {
            insertCar(car);
        }
    }

    public void insertCar(Car car) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("cars");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("ID", car.getId());
        parameters.put("TRADER_ID", car.getTrader().getId());
        parameters.put("BRAND", car.getBrand());
        parameters.put("MODEL", car.getModel());
        parameters.put("RELEASE_YEAR", car.getReleaseYear());
        parameters.put("BODY_TYPE", car.getBodyType().ordinal());
        parameters.put("ENGINE_TYPE", car.getEngineType().ordinal());
        parameters.put("DRIVE_TYPE", car.getDriveType().ordinal());
        parameters.put("TRANSMISSION_TYPE", car.getTransmissionType().ordinal());
        parameters.put("COLOR", car.getColor());
        parameters.put("MILEAGE", car.getMileage());
        parameters.put("PRICE", car.getPrice());


        simpleJdbcInsert.execute(parameters);
    }


    public List<Car> loadTradersCars() {
        return List.of(
            new Car(1L, traderRepository.findById(1L).orElseThrow(), "Lada", "Vesta", 2015, BodyType.SEDAN,
                    EngineType.PETROL, DriveType.FRONT, TransmissionType.MANUAL, "серый", 150_000, 200_000),
            new Car(2L, traderRepository.findById(1L).orElseThrow(), "Lada", "Kalina", 2010, BodyType.HATCHBACK,
                    EngineType.PETROL, DriveType.FRONT, TransmissionType.MANUAL, "синий", 220_000, 170_000),
            new Car(3L, traderRepository.findById(2L).orElseThrow(), "Audi", "Q8", 2021, BodyType.CUV,
                    EngineType.PETROL, DriveType.FULL, TransmissionType.AUTOMATIC, "белый", 70_000, 8_000_000),
            new Car(4L, traderRepository.findById(3L).orElseThrow(), "Toyota", "Camry", 2022, BodyType.SEDAN,
                    EngineType.HYBRID, DriveType.FRONT, TransmissionType.VARIATOR, "чёрный", 12, 3_950_000),
            new Car(5L, traderRepository.findById(3L).orElseThrow(), "Mercedes-Benz", "AMG GT S I", 2014, BodyType.COUPE,
                    EngineType.PETROL, DriveType.REAR, TransmissionType.ROBOT, "серый", 46_000, 8_240_000)
        );
    }
}
