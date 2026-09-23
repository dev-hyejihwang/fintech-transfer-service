# FinTech API Specification

## 1. User

### 회원가입

#### 1. API Information

| 항목             | 내용              |
| -------------- | --------------- |
| Method         | `POST`          |
| Endpoint       | `/api/v1/users` |
| Description    | 새로운 사용자를 생성한다.  |
| Authentication | 불필요             |

---

#### 2. Request

**Content-Type:** `application/json`

```json
{
  "email": "heidi@example.com",
  "password": "password123!",
  "firstName": "Heidi",
  "lastName": "Hwang"
}
```

| Field       | Type   | Required | Description |
| ----------- | ------ | -------- | ----------- |
| `email`     | String | Y        | 사용자 이메일     |
| `password`  | String | Y        | 사용자 비밀번호    |
| `firstName` | String | Y        | 이름          |
| `lastName`  | String | Y        | 성           |

---

#### 3. Response

**Success — `201 Created`**

```json
{
  "userId": 1,
  "email": "heidi@example.com",
  "firstName": "Heidi",
  "lastName": "Hwang",
  "createdAt": "2026-09-23T10:00:00"
}
```

| Field       | Type     | Description |
| ----------- | -------- | ----------- |
| `userId`    | Long     | 생성된 사용자 ID  |
| `email`     | String   | 사용자 이메일     |
| `firstName` | String   | 이름          |
| `lastName`  | String   | 성           |
| `createdAt` | DateTime | 회원가입 일시     |

---

#### 4. Error Response

##### 잘못된 요청 — `400 Bad Request`

```json
{
  "code": "INVALID_REQUEST",
  "message": "Invalid request."
}
```

##### 이메일 중복 — `409 Conflict`

```json
{
  "code": "DUPLICATE_EMAIL",
  "message": "Email already exists."
}
```

---

#### 5. Status Codes

| Status            | Description     |
| ----------------- | --------------- |
| `201 Created`     | 회원가입 성공         |
| `400 Bad Request` | 요청 데이터가 올바르지 않음 |
| `409 Conflict`    | 이미 존재하는 이메일     |

### 로그인

#### 1. API Information

| 항목             | 내용                           |
| -------------- | ---------------------------- |
| Method         | `POST`                       |
| Endpoint       | `/api/v1/auth/login`         |
| Description    | 이메일과 비밀번호를 확인하고 인증 토큰을 발급한다. |
| Authentication | 불필요                          |

---

#### 2. Request

**Content-Type:** `application/json`

```json
{
  "email": "heidi@example.com",
  "password": "password123!"
}
```

| Field      | Type   | Required | Description |
| ---------- | ------ | -------- | ----------- |
| `email`    | String | Y        | 사용자 이메일     |
| `password` | String | Y        | 사용자 비밀번호    |

---

#### 3. Response

**Success — `200 OK`**

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIs...",
  "tokenType": "Bearer"
}
```

| Field         | Type   | Description               |
| ------------- | ------ | ------------------------- |
| `accessToken` | String | 인증에 사용하는 JWT Access Token |
| `tokenType`   | String | 인증 방식 (`Bearer`)          |

---

#### 4. Error Response

##### 잘못된 요청 — `400 Bad Request`

```json
{
  "code": "INVALID_REQUEST",
  "message": "Invalid request."
}
```

##### 이메일 또는 비밀번호 불일치 — `401 Unauthorized`

```json
{
  "code": "INVALID_CREDENTIALS",
  "message": "Invalid email or password."
}
```

---

#### 5. Status Codes

| Status             | Description              |
| ------------------ | ------------------------ |
| `200 OK`           | 로그인 성공 및 Access Token 발급 |
| `400 Bad Request`  | 요청 데이터가 올바르지 않음          |
| `401 Unauthorized` | 이메일 또는 비밀번호가 올바르지 않음     |

---

## 2. Account

### 계좌 생성

#### 1. API Information

| 항목             | 내용                      |
| -------------- | ----------------------- |
| Method         | `POST`                  |
| Endpoint       | `/api/v1/accounts`      |
| Description    | 로그인한 사용자의 새로운 계좌를 생성한다. |
| Authentication | 필요                      |

---

#### 2. Request

**Content-Type:** `application/json`

Request Body는 필요하지 않다.

```http
POST /api/v1/accounts
Authorization: Bearer {accessToken}
```

| Field         | Type   | Required | Description                 |
| ------------- | ------ | -------- | --------------------------- |
| `accessToken` | String | Y        | 로그인 시 발급받은 JWT Access Token |

---

#### 3. Response

**Success — `201 Created`**

```json
{
  "accountId": 1,
  "accountNumber": "1234567890",
  "balance": 0.00,
  "createdAt": "2026-09-23T10:00:00"
}
```

| Field           | Type       | Description |
| --------------- | ---------- | ----------- |
| `accountId`     | Long       | 생성된 계좌 ID   |
| `accountNumber` | String     | 생성된 계좌번호    |
| `balance`       | BigDecimal | 계좌 잔액       |
| `createdAt`     | DateTime   | 계좌 생성 일시    |

---

#### 4. Error Response

##### 계좌 생성 실패 — `400 Bad Request`

```json
{
  "code": "ACCOUNT_CREATION_FAILED",
  "message": "Failed to create account."
}
```

##### 인증 실패 — `401 Unauthorized`

```json
{
  "code": "UNAUTHORIZED",
  "message": "Authentication is required."
}
```

---

#### 5. Status Codes

| Status             | Description        |
| ------------------ | ------------------ |
| `201 Created`      | 계좌 생성 성공           |
| `400 Bad Request`  | 잘못된 요청 또는 계좌 생성 실패 |
| `401 Unauthorized` | 인증되지 않은 사용자        |

### 내 계좌 조회

#### 1. API Information

| 항목             | 내용                     |
| -------------- | ---------------------- |
| Method         | `GET`                  |
| Endpoint       | `/api/v1/accounts`     |
| Description    | 로그인한 사용자의 모든 계좌를 조회한다. |
| Authentication | 필요                     |

---

#### 2. Request

**Request Body:** 없음

```http
GET /api/v1/accounts
Authorization: Bearer {accessToken}
```

| Field         | Type   | Required | Description                 |
| ------------- | ------ | -------- | --------------------------- |
| `accessToken` | String | Y        | 로그인 시 발급받은 JWT Access Token |

---

#### 3. Response

**Success — `200 OK`**

```json
{
  "accounts": [
    {
      "accountId": 1,
      "accountNumber": "1234567890",
      "balance": 10000.00,
      "createdAt": "2026-09-23T10:00:00"
    }
  ]
}
```

| Field           | Type       | Description |
| --------------- | ---------- | ----------- |
| `accounts`      | Array      | 사용자의 계좌 목록  |
| `accountId`     | Long       | 계좌 ID       |
| `accountNumber` | String     | 계좌번호        |
| `balance`       | BigDecimal | 계좌 잔액       |
| `createdAt`     | DateTime   | 계좌 생성 일시    |

---

#### 4. Error Response

##### 인증 실패 — `401 Unauthorized`

```json
{
  "code": "UNAUTHORIZED",
  "message": "Authentication is required."
}
```

---

#### 5. Status Codes

| Status             | Description |
| ------------------ | ----------- |
| `200 OK`           | 계좌 조회 성공    |
| `401 Unauthorized` | 인증되지 않은 사용자 |

### 계좌 상세 조회

#### 1. API Information

| 항목             | 내용                             |
| -------------- | ------------------------------ |
| Method         | `GET`                          |
| Endpoint       | `/api/v1/accounts/{accountId}` |
| Description    | 로그인한 사용자의 특정 계좌 정보를 조회한다.      |
| Authentication | 필요                             |

---

#### 2. Request

**Request Body:** 없음

```http
GET /api/v1/accounts/1
Authorization: Bearer {accessToken}
```

| Parameter     | Type   | Required | Description                 |
| ------------- | ------ | -------- | --------------------------- |
| `accountId`   | Long   | Y        | 조회할 계좌 ID                   |
| `accessToken` | String | Y        | 로그인 시 발급받은 JWT Access Token |

---

#### 3. Response

**Success — `200 OK`**

```json
{
  "accountId": 1,
  "accountNumber": "1234567890",
  "balance": 10000.00,
  "createdAt": "2026-09-23T10:00:00"
}
```

| Field           | Type       | Description |
| --------------- | ---------- | ----------- |
| `accountId`     | Long       | 계좌 ID       |
| `accountNumber` | String     | 계좌번호        |
| `balance`       | BigDecimal | 현재 계좌 잔액    |
| `createdAt`     | DateTime   | 계좌 생성 일시    |

---

#### 4. Error Response

##### 인증 실패 — `401 Unauthorized`

```json
{
  "code": "UNAUTHORIZED",
  "message": "Authentication is required."
}
```

##### 계좌 없음 또는 접근 권한 없음 — `404 Not Found`

```json
{
  "code": "ACCOUNT_NOT_FOUND",
  "message": "Account not found."
}
```

---

#### 5. Status Codes

| Status             | Description                 |
| ------------------ | --------------------------- |
| `200 OK`           | 계좌 상세 조회 성공                 |
| `401 Unauthorized` | 인증되지 않은 사용자                 |
| `404 Not Found`    | 계좌가 존재하지 않거나 해당 사용자의 계좌가 아님 |

### 입금

#### 1. API Information

| 항목             | 내용                                     |
| -------------- | -------------------------------------- |
| Method         | `POST`                                 |
| Endpoint       | `/api/v1/accounts/{accountId}/deposit` |
| Description    | 로그인한 사용자의 계좌에 금액을 입금한다.                |
| Authentication | 필요                                     |

---

#### 2. Request

**Content-Type:** `application/json`

```http
POST /api/v1/accounts/1/deposit
Authorization: Bearer {accessToken}
```

```json
{
  "amount": 10000.00
}
```

| Field       | Type       | Required | Description |
| ----------- | ---------- | -------- | ----------- |
| `accountId` | Long       | Y        | 입금할 계좌 ID   |
| `amount`    | BigDecimal | Y        | 입금 금액       |

---

#### 3. Response

**Success — `200 OK`**

```json
{
  "accountId": 1,
  "accountNumber": "1234567890",
  "amount": 10000.00,
  "balance": 20000.00
}
```

| Field           | Type       | Description |
| --------------- | ---------- | ----------- |
| `accountId`     | Long       | 계좌 ID       |
| `accountNumber` | String     | 계좌번호        |
| `amount`        | BigDecimal | 입금된 금액      |
| `balance`       | BigDecimal | 입금 후 계좌 잔액  |

---

#### 4. Error Response

##### 잘못된 입금 금액 — `400 Bad Request`

```json
{
  "code": "INVALID_AMOUNT",
  "message": "Deposit amount must be greater than zero."
}
```

##### 인증 실패 — `401 Unauthorized`

```json
{
  "code": "UNAUTHORIZED",
  "message": "Authentication is required."
}
```

##### 계좌 없음 또는 접근 권한 없음 — `404 Not Found`

```json
{
  "code": "ACCOUNT_NOT_FOUND",
  "message": "Account not found."
}
```

---

#### 5. Status Codes

| Status             | Description                 |
| ------------------ | --------------------------- |
| `200 OK`           | 입금 성공                       |
| `400 Bad Request`  | 입금 금액이 올바르지 않음              |
| `401 Unauthorized` | 인증되지 않은 사용자                 |
| `404 Not Found`    | 계좌가 존재하지 않거나 해당 사용자의 계좌가 아님 |

---

## 3. Transfer

### 송금

#### 1. API Information

| 항목             | 내용                         |
| -------------- | -------------------------- |
| Method         | `POST`                     |
| Endpoint       | `/api/v1/transfers`        |
| Description    | 로그인한 사용자가 다른 계좌로 금액을 송금한다. |
| Authentication | 필요                         |

---

#### 2. Request

**Content-Type:** `application/json`

```http
POST /api/v1/transfers
Authorization: Bearer {accessToken}
```

```json
{
  "fromAccountNumber": "1234567890",
  "toAccountNumber": "9876543210",
  "amount": 5000.00
}
```

| Field               | Type       | Required | Description |
| ------------------- | ---------- | -------- | ----------- |
| `fromAccountNumber` | String     | Y        | 출금 계좌번호     |
| `toAccountNumber`   | String     | Y        | 입금 계좌번호     |
| `amount`            | BigDecimal | Y        | 송금 금액       |

---

#### 3. Response

**Success — `200 OK`**

```json
{
  "transferId": 1,
  "fromAccountNumber": "1234567890",
  "toAccountNumber": "9876543210",
  "amount": 5000.00,
  "status": "COMPLETED"
}
```

| Field               | Type       | Description |
| ------------------- | ---------- | ----------- |
| `transferId`        | Long       | 송금 ID       |
| `fromAccountNumber` | String     | 출금 계좌번호     |
| `toAccountNumber`   | String     | 입금 계좌번호     |
| `amount`            | BigDecimal | 송금 금액       |
| `status`            | String     | 송금 상태       |

---

#### 4. Error Response

##### 잘못된 송금 금액 — `400 Bad Request`

```json
{
  "code": "INVALID_AMOUNT",
  "message": "Transfer amount must be greater than zero."
}
```

##### 인증 실패 — `401 Unauthorized`

```json
{
  "code": "UNAUTHORIZED",
  "message": "Authentication is required."
}
```

##### 계좌 없음 또는 접근 권한 없음 — `404 Not Found`

```json
{
  "code": "ACCOUNT_NOT_FOUND",
  "message": "Account not found."
}
```

##### 잔액 부족 — `409 Conflict`

```json
{
  "code": "INSUFFICIENT_BALANCE",
  "message": "Insufficient balance."
}
```

---

#### 5. Status Codes

| Status             | Description                        |
| ------------------ | ---------------------------------- |
| `200 OK`           | 송금 성공                              |
| `400 Bad Request`  | 송금 금액이 올바르지 않음                     |
| `401 Unauthorized` | 인증되지 않은 사용자                        |
| `404 Not Found`    | 계좌가 존재하지 않거나 출금 계좌가 해당 사용자의 계좌가 아님 |
| `409 Conflict`     | 잔액 부족 또는 송금 처리 충돌                  |

---

## 4. Transaction

### 거래내역 조회

#### 1. API Information

| 항목             | 내용                                          |
| -------------- | ------------------------------------------- |
| Method         | `GET`                                       |
| Endpoint       | `/api/v1/accounts/{accountId}/transactions` |
| Description    | 로그인한 사용자의 특정 계좌 거래내역을 조회한다.                 |
| Authentication | 필요                                          |

---

#### 2. Request

```http
GET /api/v1/accounts/1/transactions
Authorization: Bearer {accessToken}
```

| Field       | Type    | Required | Description     |
| ----------- | ------- | -------- | --------------- |
| `accountId` | Long    | Y        | 거래내역을 조회할 계좌 ID |
| `page`      | Integer | N        | 페이지 번호          |
| `size`      | Integer | N        | 페이지당 거래내역 수     |

**Query Parameter 예시:**

```http
GET /api/v1/accounts/1/transactions?page=0&size=20
Authorization: Bearer {accessToken}
```

---

#### 3. Response

**Success — `200 OK`**

```json
{
  "content": [
    {
      "transactionId": 1,
      "type": "TRANSFER",
      "amount": 5000.00,
      "status": "COMPLETED",
      "createdAt": "2026-09-23T10:30:00"
    }
  ],
  "page": 0,
  "size": 20,
  "totalElements": 1,
  "totalPages": 1
}
```

| Field           | Type          | Description |
| --------------- | ------------- | ----------- |
| `transactionId` | Long          | 거래 ID       |
| `type`          | String        | 거래 유형       |
| `amount`        | BigDecimal    | 거래 금액       |
| `status`        | String        | 거래 상태       |
| `createdAt`     | LocalDateTime | 거래 일시       |
| `page`          | Integer       | 현재 페이지 번호   |
| `size`          | Integer       | 페이지당 거래내역 수 |
| `totalElements` | Long          | 전체 거래내역 수   |
| `totalPages`    | Integer       | 전체 페이지 수    |

---

#### 4. Error Response

##### 인증 실패 — `401 Unauthorized`

```json
{
  "code": "UNAUTHORIZED",
  "message": "Authentication is required."
}
```

##### 계좌 없음 또는 접근 권한 없음 — `404 Not Found`

```json
{
  "code": "ACCOUNT_NOT_FOUND",
  "message": "Account not found."
}
```

---

#### 5. Status Codes

| Status             | Description                 |
| ------------------ | --------------------------- |
| `200 OK`           | 거래내역 조회 성공                  |
| `401 Unauthorized` | 인증되지 않은 사용자                 |
| `404 Not Found`    | 계좌가 존재하지 않거나 해당 사용자의 계좌가 아님 |
