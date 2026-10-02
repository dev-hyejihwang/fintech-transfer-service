package com.fintech.fintech_transfer_service.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void register_success() {

        String email = "heidi@test.com";
        String password = "1234";
        String firstName = "Heidi";
        String lastName = "Hwang";

        when(userRepository.existsByEmail(email))
                .thenReturn(false);

        User savedUser = User.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .build();

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        User result = userService.register(
                email,
                password,
                firstName,
                lastName
        );

        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getFirstName()).isEqualTo(firstName);
        assertThat(result.getLastName()).isEqualTo(lastName);

        verify(userRepository).existsByEmail(email);
        verify(userRepository).save(any(User.class));
    }


    @Test
    void register_duplicateEmail() {
        String email = "heidi@test.com";

        when(userRepository.existsByEmail(email))
                .thenReturn(true);

        assertThatThrownBy(() ->
                userService.register(
                        email,
                        "1234",
                        "Heidi",
                        "Hwang"
                )
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email already exists.");

        verify(userRepository).existsByEmail(email);

        verify(userRepository, never()).save(any(User.class));
    }


}
