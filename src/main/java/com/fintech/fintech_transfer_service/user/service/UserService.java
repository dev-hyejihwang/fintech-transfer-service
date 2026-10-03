package com.fintech.fintech_transfer_service.user.service;

import com.fintech.fintech_transfer_service.user.domain.User;
import com.fintech.fintech_transfer_service.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User register(String email, String password, String firstName, String lastName) {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists.");
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        return userRepository.save(user);
    }

}
