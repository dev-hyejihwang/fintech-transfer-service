package com.fintech.fintech_transfer_service.account;

import com.fintech.fintech_transfer_service.user.domain.User;
import com.fintech.fintech_transfer_service.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Account createAccount(String email, String accountNumber) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found.")
                );

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .balance(0L)
                .user(user)
                .build();

        return accountRepository.save(account);
    }

    public Long getBalance(String accountNumber) {

        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new IllegalArgumentException("Account not found.")
                );

        return account.getBalance();
    }
}
