package ru.carsalon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CarSalonApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarSalonApplication.class, args);
	}
}
