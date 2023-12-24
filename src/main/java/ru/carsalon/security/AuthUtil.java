package ru.carsalon.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.carsalon.entity.UserInfo;

public class AuthUtil {

    public static UserInfo getUserFromContext() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            return (UserInfo) user;
        }
        return null;

    }
}
