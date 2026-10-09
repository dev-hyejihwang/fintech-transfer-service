package com.fintech.fintech_transfer_service.account;

import com.fintech.fintech_transfer_service.user.domain.User;
import com.fintech.fintech_transfer_service.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional
    public void deposit(String accountNumber, Long amount) {
        Account account = accountRepository.findByAccountNumberWithLock(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        account.deposit(amount);
    }

    @Transactional
    public void withdraw(String accountNumber, Long amount) {
        Account account = accountRepository.findByAccountNumberWithLock(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));

        account.withdraw(amount);
    }
}
