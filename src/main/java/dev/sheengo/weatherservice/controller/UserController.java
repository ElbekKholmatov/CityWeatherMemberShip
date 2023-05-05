package dev.sheengo.weatherservice.controller;

import dev.sheengo.weatherservice.domains.City;
import dev.sheengo.weatherservice.service.AuthService;
import dev.sheengo.weatherservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/getMemberShip")
    public ResponseEntity<Void> getMemberShip(
            List<String> cities
    ) {
        userService.getMemberShip(cities);
    }
}
