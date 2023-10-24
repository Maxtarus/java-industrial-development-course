package ru.carsalon.controller;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import ru.carsalon.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.carsalon.entity.Trader;

@Slf4j
@Controller
@RequestMapping("/cars/sell")
public class SellCarController {

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

        log.info("\nИнформация о машине: {}\nИнформация о продавце: {}", car, trader);
        return "redirect:/cars/sell/successful_publication";
    }

    @GetMapping("/successful_publication")
    public String showSuccessfulPublicationPage() {
        return "successful_publication";
    }
}
