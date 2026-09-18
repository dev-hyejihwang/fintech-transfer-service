# 💳 Fintech Transfer Service (Backend)

[🇰🇷 KO](#-한국어-소개) | [EN English](#-english-introduction)

---

## 🇰🇷 한국어 소개

### 1. 프로젝트 개요
* **도메인:** 핀테크 (간편 송금 및 계좌 관리)
* **목표:** 동시성 제어와 안전한 트랜잭션 처리가 보장되는 백엔드 서버 구축

### 2. 핵심 유저 플로우
* 회원가입 및 계좌 생성
* 잔액 충전
* 계좌 송금 (동시성/트랜잭션 챌린지)
* 거래 내역 조회 (페이징 처리)

### 3. MVP 범위
* 회원가입 / 로그인 (인증)
* 계좌 잔액 충전 및 조회
* 타인 계좌 송금 (동시성 이슈 방어)
* 거래 내역 리스트 페이징 조회

### 4. 기술 스택 (Tech Stack)
* **Language & Framework:** Java 17+, Spring Boot 4.x
* **Database & ORM:** MySQL, Spring Data JPA / QueryDSL
* **Cache & Concurrency:** Redis
* **Infrastructure:** Docker

### 5. 핵심 기술 챌린지 (Technical Challenge)
* **주제:** 대규모 송금 및 잔액 충전 상황에서의 동시성 제어 (Concurrency Control)
* **목표:**
  * 동시에 여러 송금/충전 요청이 발생할 때 발생할 수 있는 Race Condition 방어
  * Pessimistic/Optimistic Lock 및 Redis 분산 락 적용을 통한 데이터 무결성(Data Integrity) 확보

### 6. 핵심 기술 용어 정리
* **Robust:** 에러나 대용량 트래픽 속에서도 시스템이 죽지 않고 견고하게 버티는 상태
* **Concurrency Control:** 여러 사용자가 동시에 데이터를 수정할 때 꼬이지 않도록 조율하는 기술
* **Data Integrity / Consistency:** 데이터가 모순 없이 항상 올바르고 정확한 상태를 유지하는 것
* **Scalability:** 사용자가 늘어나거나 규모가 커졌을 때 시스템을 쉽게 확장할 수 있는 성질
* **Pagination:** 수많은 데이터를 한 번에 불러오지 않고 일정 크기로 끊어서 보여주는 기술


---

## english-introduction

### 1. Project Overview
* **Domain:** FinTech (Simple Remittance & Account Management System)
* **Goal:** Building a robust backend server with a strong focus on concurrency control and reliable transaction management.

### 2. Core User Flow
* Sign Up & Account Creation
* Deposit
* Remittance (Concurrency & Race Condition Handling)
* Transaction History Pagination

### 3. MVP Scope
* Sign Up & Authentication (Login)
* Account Balance Deposit & Inquiry
* Peer-to-Peer Transfer (Concurrency & Race Condition Handling)
* Transaction History Pagination

### 4. Tech Stack
* **Language & Framework:** Java 17+, Spring Boot 4.x
* **Database & ORM:** MySQL, Spring Data JPA / QueryDSL
* **Cache & Concurrency:** Redis
* **Infrastructure:** Docker

### 5. Core Technical Challenge
* **Topic:** Concurrency Control & Race Condition Handling in Remittance/Deposit
* **Goal:**
  * Preventing race conditions during simultaneous transfer and deposit requests.
  * Ensuring Data Integrity through Pessimistic/Optimistic Locking or Redis Distributed Locks.

### 6. Key Technical Vocabulary
* **Robust:** The ability of a system to withstand errors or high traffic loads without crashing.
* **Concurrency Control:** Managing simultaneous data access to prevent race conditions.
* **Data Integrity / Consistency:** Ensuring data remains accurate and reliable across transactions.
* **Scalability:** The capability of a system to handle a growing amount of work.
* **Pagination:** Breaking down large datasets into manageable chunks.