package com.fintech.fintech_transfer_service;

import com.fintech.fintech_transfer_service.account.Account;
import com.fintech.fintech_transfer_service.account.AccountRepository;
import com.fintech.fintech_transfer_service.user.User;
import com.fintech.fintech_transfer_service.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    void saveUserAndAccountTest() {
        User user = User.builder()
                .email("heidi@test.com")
                .password("1234")
                .firstName("Heidi")
                .lastName("Hwang")
                .build();

        User savedUser = userRepository.save(user);

        Account account = Account.builder()
                .accountNumber("110-123-456789")
                .balance(10000L)
                .user(savedUser)
                .build();

        Account savedAccount = accountRepository.save(account);

        Account foundAccount = accountRepository.findByAccountNumber("110-123-456789").orElseThrow();
        assertThat(foundAccount.getBalance()).isEqualTo(10000L);
        assertThat(foundAccount.getUser().getEmail()).isEqualTo("heidi@test.com");

        System.out.println("성공! 계좌 주인: " + foundAccount.getUser().getFirstName() + ", 잔액: " + foundAccount.getBalance());
    }
}
