package com.fintech.fintech_transfer_service.account;

import com.fintech.fintech_transfer_service.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    void createAccount_userNotFound() {
        // given
        String email = "notfound@test.com";
        String accountNumber = "123456789";

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() ->
                accountService.createAccount(email, accountNumber)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User not found.");

        verify(userRepository).findByEmail(email);

        verify(accountRepository, never())
                .save(any(Account.class));
    }


    @Test
    void getBalance_accountNotFound() {
        // given
        String accountNumber = "999999999";

        when(accountRepository.findByAccountNumber(accountNumber))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() ->
                accountService.getBalance(accountNumber)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Account not found.");

        verify(accountRepository)
                .findByAccountNumber(accountNumber);
    }
}
