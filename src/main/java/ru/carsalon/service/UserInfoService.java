package ru.carsalon.service;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.carsalon.configuration.ApplicationConfig;
import ru.carsalon.entity.RoleInfo;
import ru.carsalon.entity.RoleType;
import ru.carsalon.entity.UserInfo;
import ru.carsalon.repository.RoleInfoRepository;
import ru.carsalon.repository.UserRepository;
import ru.carsalon.security.jwt.JwtUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final RoleInfoRepository roleInfoRepository;

    private final JwtUtils jwtUtils;

    private final ApplicationConfig config;

    public UserInfo save(UserInfo user) {
        return userRepository.save(user);
    }

    public Cookie getAuthorizeCookie(UserInfo userInfo) {
        String jwtToken = jwtUtils.generateTokenFromEmail(userInfo.getUsername());
        return getCookie(config.jwt().headerName(), jwtToken, Math.toIntExact(config.jwt().expiration() / 1000));
    }

    public Cookie getCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    @Transactional
    public UserInfo signIn(String username, String password) {
        Optional<UserInfo> userInfoOptional = userRepository.findByUsername(username);
        if (userInfoOptional.isPresent()) {
            UserInfo userInfo = userInfoOptional.get();
            if (passwordEncoder.matches(password, userInfo.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userInfo.getUsername(), password)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return userInfo;
            }
        }
        return null;
    }

    @Transactional
    public UserInfo signUp(String username, String password) {
        UserInfo user = new UserInfo()
                .setUsername(username)
                .setPassword(passwordEncoder.encode(password));

        RoleInfo userRole = roleInfoRepository.findByName(RoleType.USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.getRoles().add(userRole);
        user = userRepository.save(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return user;
    }
}
