# 💳 Fintech Transfer Service (Backend)

## 1. Project Overview
- **Domain:** FinTech (Simple Remittance & Account Management System)
- **Goal:** Building a robust backend server with a strong focus on concurrency control and reliable transaction management.

## 2. Core User Flow
1. Sign Up & Account Creation
2. Deposit
3. Remittance (Concurrency & Race Condition Handling)
4. Transaction History Pagination

## 3. MVP Scope
- User Sign Up & Authentication (Login)
- Account Balance Deposit & Inquiry
- Peer-to-Peer Transfer (Concurrency & Race Condition Handling)
- Transaction History Pagination

## 4. Tech Stack
- **Language & Framework:** Java 17+, Spring Boot 3.x
- **Database & ORM:** MySQL, Spring Data JPA / QueryDSL
- **Cache & Concurrency:** Redis
- **Infrastructure:** Docker

## 5. Core Technical Challenge
- **Topic:** Concurrency Control & Race Condition Handling in Remittance/Deposit
- **Goal:**
  - Preventing race conditions during simultaneous transfer and deposit requests.
  - Ensuring **Data Integrity** through Pessimistic/Optimistic Locking or Redis Distributed Locks.

## 6. Key Technical Vocabulary
- **Robust:** The ability of a system to withstand errors or high traffic loads without crashing.
- **Concurrency Control:** Managing simultaneous data access to prevent race conditions.
- **Data Integrity / Consistency:** Ensuring data remains accurate and reliable across transactions.
- **Scalability:** The capability of a system to handle a growing amount of work.
- **Pagination:** Breaking down large datasets into manageable chunks.
