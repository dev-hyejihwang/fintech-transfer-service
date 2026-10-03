package com.fintech.fintech_transfer_service.account;

import com.fintech.fintech_transfer_service.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String accountNumber;

    @Column(nullable = false)
    private Long balance;

    @Column(name = "created_at", insertable = false, updatable = false) // 3. created_at 매핑 추가
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Account(String accountNumber, Long balance, User user) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.user = user;
    }

    public void deposit(Long amount) {
        this.balance += amount;
    }

    public void withdraw(Long amount) {
        if (this.balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.balance -= amount;
    }
}