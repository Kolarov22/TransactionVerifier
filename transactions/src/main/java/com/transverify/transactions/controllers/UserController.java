package com.transverify.transactions.controllers;

import com.transverify.transactions.domain.dto.user.RegisterUserRequestDTO;
import com.transverify.transactions.domain.entities.User;
import com.transverify.transactions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody RegisterUserRequestDTO registerUserRequestDTO) {
        User user = new User();

        user.setFirstName(registerUserRequestDTO.getFirstName());
        user.setLastName(registerUserRequestDTO.getLastName());
        user.setEmail(registerUserRequestDTO.getEmail());
        user.setIBAN(registerUserRequestDTO.getIBAN());

        userService.addUser(user);
        return ResponseEntity.ok().build();
    }
}
