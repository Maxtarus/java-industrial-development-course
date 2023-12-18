package ru.carsalon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.carsalon.entity.car.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.carsalon.entity.Trader;
import ru.carsalon.service.CarService;
import ru.carsalon.service.TraderService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cars/sell")
public class SellCarController {
    private final CarService carService;
    private final TraderService traderService;

    @GetMapping
    public String showSellCarForm(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("trader", new Trader());
        return "sell_car";
    }

    @PostMapping
    public String publishAd(@Valid Car car, Errors carErrors,
                            @Valid Trader trader, Errors traderErrors) {
        if (carErrors.hasErrors() || traderErrors.hasErrors()) {
            return "sell_car";
        }
        List<Car> carList = trader.getCars();
        carList.add(car);
        trader.setCars(carList);
        car.setTrader(trader);
        traderService.save(trader);


        log.info("\nИнформация о машине: {}\nИнформация о продавце: {}", car, trader);
        return "redirect:/cars/sell/successful_publication";
    }

    @GetMapping("/successful_publication")
    public String showSuccessfulPublicationPage() {
        return "successful_publication";
    }
}
