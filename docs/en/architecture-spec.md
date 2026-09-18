# Database Schema & API Architecture Design

## 1. Overview

This document defines the core database schema, entity relationships, and draft REST API specifications for a fintech remittance and account management service.

The design focuses on **data integrity, transaction stability, and concurrency control**.

---

## 2. Database Schema and ERD Design

### 2.1 Core Entities and Relationships

- **User (1) ─── (N) Account:** A user can own multiple financial accounts.
- **Account ─── Transaction:** All deposit and transfer records are tracked through foreign key mappings.

### 2.2 Entity Specifications

- **User**
    - `id` (Primary Key, Long)
    - `email` (Email, Unique)
    - `password` (Password)
    - `name` (Name)
- **Account**
    - `id` (Primary Key, Long)
    - `user_id` (Foreign Key, Long)
    - `account_number` (Account Number, Unique)
    - `balance` (Balance, BigDecimal) - Core target for concurrency control
- **Transaction**
    - `id` (Primary Key, Long)
    - `sender_account_id` (Foreign Key, Long, Nullable - not used for deposits)
    - `receiver_account_id` (Foreign Key, Long)
    - `amount` (Transaction Amount, BigDecimal)
    - `type` (Transaction Type: Deposit, Transfer)
    - `created_at` (Created At)

---

## 3. Design Decisions and Rationale

- **Separation of User and Account:** Separates user profile management from financial account state management to support future multi-account expansion.
- **Concurrency Control and Transaction Management:** Designed with strict transaction management and concurrency control in mind to prevent data races that may occur when multiple users make deposit and transfer requests simultaneously. Potential approaches include pessimistic locking, optimistic locking, or Redis distributed locking.
- **Multiple Foreign Keys in Transaction:** Stores both the sender and receiver accounts to clearly represent the direction of funds in P2P transfers.

---

## 4. REST API Specification

### 4.1 User and Authentication

- **Sign Up:** `POST /api/v1/users`
- **Login:** `POST /api/v1/auth/login` *(Security and token-based authentication under consideration)*

### 4.2 Account and Balance

- **Create Account:** `POST /api/v1/accounts`
- **Check Balance:** `GET /api/v1/accounts/{accountId}`

### 4.3 Deposits and Transfers (Core Challenge)

- **Deposit:** `POST /api/v1/accounts/{accountId}/deposits` *(Concurrency control point)*
- **Transfer:** `POST /api/v1/transfers` *(Core target for preventing concurrency issues and applying locking/distributed locking)*

### 4.4 Transaction History

- **Paginated Transaction History:** `GET /api/v1/accounts/{accountId}/transactions?page=0&size=20`

---

## 5. Exception Handling Strategy

- **Input and Client Errors:** Duplicate email, invalid login credentials, non-existent account or user, amount less than or equal to zero
- **Business and State Errors:** Insufficient balance, attempting to transfer to the same account, unauthenticated access
- **Concurrency Errors:** Data race conditions that may occur during concurrent deposits and transfers in a multi-request environment.