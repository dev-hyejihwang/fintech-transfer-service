package com.fintech.fintech_transfer_service.user.controller;

import com.fintech.fintech_transfer_service.user.service.UserService;
import com.fintech.fintech_transfer_service.user.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> register(@RequestBody UserSaveRequestDto request) {
        userService.register(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
