package com.fintech.fintech_transfer_service.user;

import com.fintech.fintech_transfer_service.user.domain.User;
import com.fintech.fintech_transfer_service.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveUser() {

        User user = User.builder()
                .email("test@test.com")
                .password("1234")
                .firstName("Heidi")
                .lastName("Hwang")
                .build();

        User savedUser = userRepository.save(user);

        entityManager.clear();
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@test.com");
        assertThat(foundUser.get().getCreatedAt()).isNotNull();
    }


}
