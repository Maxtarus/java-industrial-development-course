package ru.carsalon.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.carsalon.configuration.ApplicationConfig;
import ru.carsalon.entity.RoleType;
import ru.carsalon.entity.UserInfo;
import ru.carsalon.service.UserInfoService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserInfoService userInfoService;
    private final ApplicationConfig config;

    @GetMapping("/")
    public ModelAndView loginPage() {
        ModelAndView view = new ModelAndView("login");
        view.addObject("userInfo", new UserInfo());
        return view;
    }

    @PostMapping("/auth/signIn")
    public Object signIn(
            @ModelAttribute @Valid UserInfo user,
            BindingResult result,
            HttpServletResponse response,
            HttpSession session
    ) throws IOException {
        if (result.hasErrors()) {
            return "login";
        }
        UserInfo userInfo = userInfoService.signIn(user.getUsername(), user.getPassword());
        if (userInfo == null) {
            result.rejectValue("username", "", "Неверное значение");
            return "login";
        }

        session.setAttribute("user", userInfo);
        response.addCookie(userInfoService.getAuthorizeCookie(userInfo));

        List<RoleType> roles = userInfo.getEnumRoles();
        response.sendRedirect("/car_salon/cars_list");
//        if (roles.contains(RoleType.ADMIN)) {
//            response.sendRedirect("/car_salon/sell");
//        } else {
//            response.sendRedirect("/car_salon/carsList");
//        }
        return null;
    }

    @PostMapping("/auth/signUp")
    public Object signUp(
            @ModelAttribute @Valid UserInfo user,
            BindingResult result,
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session
    ) throws IOException {
        if (result.hasErrors()) {
            return "login";
        }
        UserInfo userInfo;
        try {
            userInfo = userInfoService.signUp(user.getUsername(), user.getPassword());
            session.setAttribute("user", userInfo);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.rejectValue("username", "", "Неверное значение");
            return "login";
        }

        response.addCookie(userInfoService.getAuthorizeCookie(userInfo));
        response.sendRedirect("/car_salon/cars_list");
        return null;
    }

    @GetMapping("/auth/logout")
    public void logout(
            HttpServletResponse response,
            HttpServletRequest request,
            HttpSession session
    ) throws IOException {
        Optional<Cookie> cookieOptional = Arrays.stream(request.getCookies())
                .filter(c -> config.jwt().headerName().equals(c.getName()))
                .findAny();
        if (cookieOptional.isPresent()) {
            Cookie cook = userInfoService.getCookie(config.jwt().headerName(), null, 0);
            response.addCookie(cook);
            session.invalidate();
            response.sendRedirect("/");
        }
    }
}
