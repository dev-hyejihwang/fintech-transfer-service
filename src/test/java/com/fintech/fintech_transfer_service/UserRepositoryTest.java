package com.fintech.fintech_transfer_service;

import com.fintech.fintech_transfer_service.user.User;
import com.fintech.fintech_transfer_service.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {

        User user = User.builder()
                .email("test@test.com")
                .password("1234")
                .firstName("Heidi")
                .lastName("Hwang")
                .build();

        User savedUser = userRepository.save(user);

        System.out.println(savedUser.getId());
    }

    @Test
    void findById() {
        User user = userRepository.findById(1L)
                .orElseThrow();

        System.out.println(user.getEmail());
    }

}
