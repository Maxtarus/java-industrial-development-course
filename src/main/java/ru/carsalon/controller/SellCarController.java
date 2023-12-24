package ru.carsalon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.carsalon.entity.UserInfo;
import ru.carsalon.entity.car.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.carsalon.entity.Trader;
import ru.carsalon.security.AuthUtil;
import ru.carsalon.service.CarService;
import ru.carsalon.service.TraderService;
import ru.carsalon.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/car_salon")
public class SellCarController {
    private final TraderService traderService;
    private final CarService carService;
    private final UserInfoService userInfoService;

    @GetMapping("/sell")
    public String showSellCarForm(Model model) {
        model.addAttribute("car", new Car());
        UserInfo user = AuthUtil.getUserFromContext();

        if (Objects.requireNonNull(user).getTrader() != null) {
            model.addAttribute("trader", user.getTrader());
        } else {
            model.addAttribute("trader", new Trader());
        }

        return "sell_car";
    }

    @PostMapping("/sell_car")
    public String publishAd(@Valid Car car, Errors carErrors,
                            @Valid Trader trader, Errors traderErrors, Model model) {
        if (carErrors.hasErrors() || traderErrors.hasErrors()) {
            return "sell_car";
        }

        UserInfo user = AuthUtil.getUserFromContext();

        if (Objects.requireNonNull(user).getTrader() != null) {
            trader = user.getTrader();
        }

        List<Car> carList = trader.getCars();
        carList.add(car);
        trader.setCars(carList);
        car.setTrader(trader);
        Trader traderObject = traderService.save(trader);

        Objects.requireNonNull(user).setTrader(traderObject);
        userInfoService.save(user);

        log.info("\nИнформация о машине: {}\nИнформация о продавце: {}", car, trader);
        model.addAttribute("cars", carService.findAll());
        return "cars_list";
//        return "redirect:/cars/sell/successful_publication";
    }

//    @GetMapping("/successful_publication")
//    public String showSuccessfulPublicationPage() {
//        return "successful_publication";
//    }

    @GetMapping("/cars_list")
    public String carsList(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "cars_list";
    }
}
