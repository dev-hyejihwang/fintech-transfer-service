## 1. User

* Sign Up

  ### 1. API Information

  | Item           | Description                 |
  | -------------- | --------------------------- |
  | Method         | `POST`                      |
  | Endpoint       | `/api/v1/users`             |
  | Description    | Creates a new user account. |
  | Authentication | Not required                |

  ---

  ### 2. Request

  **Content-Type:** `application/json`

  ```json
  {
    "email": "heidi@example.com",
    "password": "password123!",
    "firstName": "Heidi",
    "lastName": "Hwang"
  }
  ```

  | Field       | Type   | Required | Description        |
  | ----------- | ------ | -------- | ------------------ |
  | `email`     | String | Y        | User email address |
  | `password`  | String | Y        | User password      |
  | `firstName` | String | Y        | User's first name  |
  | `lastName`  | String | Y        | User's last name   |

  ---

  ### 3. Response

  #### Success — `201 Created`

  ```json
  {
    "userId": 1,
    "email": "heidi@example.com",
    "firstName": "Heidi",
    "lastName": "Hwang",
    "createdAt": "2026-09-23T10:00:00"
  }
  ```

  | Field       | Type          | Description        |
  | ----------- | ------------- | ------------------ |
  | `userId`    | Long          | User ID            |
  | `email`     | String        | User email address |
  | `firstName` | String        | User's first name  |
  | `lastName`  | String        | User's last name   |
  | `createdAt` | LocalDateTime | User creation time |

  ---

  ### 4. Error Response

  #### Invalid Request — `400 Bad Request`

  ```json
  {
    "code": "INVALID_REQUEST",
    "message": "Invalid request."
  }
  ```

  #### Duplicate Email — `409 Conflict`

  ```json
  {
    "code": "EMAIL_ALREADY_EXISTS",
    "message": "Email already exists."
  }
  ```

  ---

  ### 5. Status Codes

  | Status            | Description                 |
  | ----------------- | --------------------------- |
  | `201 Created`     | User created successfully   |
  | `400 Bad Request` | Request data is invalid     |
  | `409 Conflict`    | Email is already registered |

* Login

  ### 1. API Information

  | Item           | Description                                      |
  | -------------- | ------------------------------------------------ |
  | Method         | `POST`                                           |
  | Endpoint       | `/api/v1/auth/login`                             |
  | Description    | Authenticates a user and issues an access token. |
  | Authentication | Not required                                     |

  ---

  ### 2. Request

  **Content-Type:** `application/json`

  ```json
  {
    "email": "heidi@example.com",
    "password": "password123!"
  }
  ```

  | Field      | Type   | Required | Description        |
  | ---------- | ------ | -------- | ------------------ |
  | `email`    | String | Y        | User email address |
  | `password` | String | Y        | User password      |

  ---

  ### 3. Response

  #### Success — `200 OK`

  ```json
  {
    "accessToken": "eyJhbGciOiJIUzI1NiIs...",
    "tokenType": "Bearer"
  }
  ```

  | Field         | Type   | Description                          |
  | ------------- | ------ | ------------------------------------ |
  | `accessToken` | String | JWT access token                     |
  | `tokenType`   | String | Authentication token type (`Bearer`) |

  ---

  ### 4. Error Response

  #### Invalid Request — `400 Bad Request`

  ```json
  {
    "code": "INVALID_REQUEST",
    "message": "Invalid request."
  }
  ```

  #### Invalid Credentials — `401 Unauthorized`

  ```json
  {
    "code": "INVALID_CREDENTIALS",
    "message": "Invalid email or password."
  }
  ```

  ---

  ### 5. Status Codes

  | Status             | Description                    |
  | ------------------ | ------------------------------ |
  | `200 OK`           | Login successful               |
  | `400 Bad Request`  | Request data is invalid        |
  | `401 Unauthorized` | Email or password is incorrect |

## 2. Account

* Create Account

  ### 1. API Information

  | Item           | Description                                       |
  | -------------- | ------------------------------------------------- |
  | Method         | `POST`                                            |
  | Endpoint       | `/api/v1/accounts`                                |
  | Description    | Creates a new account for the authenticated user. |
  | Authentication | Required                                          |

  ---

  ### 2. Request

  **Content-Type:** `application/json`

  **Request Body:** None

  ```http
  POST /api/v1/accounts
  Authorization: Bearer {accessToken}
  ```

  | Field         | Type   | Required | Description                         |
  | ------------- | ------ | -------- | ----------------------------------- |
  | `accessToken` | String | Y        | JWT Access Token issued after login |

  ---

  ### 3. Response

  #### Success — `201 Created`

  ```json
  {
    "accountId": 1,
    "accountNumber": "1234567890",
    "balance": 0.00,
    "createdAt": "2026-09-23T10:00:00"
  }
  ```

  | Field           | Type          | Description           |
  | --------------- | ------------- | --------------------- |
  | `accountId`     | Long          | Account ID            |
  | `accountNumber` | String        | Account number        |
  | `balance`       | BigDecimal    | Account balance       |
  | `createdAt`     | LocalDateTime | Account creation time |

  ---

  ### 4. Error Response

  #### Account Creation Failed — `400 Bad Request`

  ```json
  {
    "code": "ACCOUNT_CREATION_FAILED",
    "message": "Account creation failed."
  }
  ```

  #### Authentication Failed — `401 Unauthorized`

  ```json
  {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required."
  }
  ```

  ---

  ### 5. Status Codes

  | Status             | Description                  |
  | ------------------ | ---------------------------- |
  | `201 Created`      | Account created successfully |
  | `400 Bad Request`  | Account creation failed      |
  | `401 Unauthorized` | User is not authenticated    |

* Get My Accounts

  ### 1. API Information

  | Item           | Description                                                 |
  | -------------- | ----------------------------------------------------------- |
  | Method         | `GET`                                                       |
  | Endpoint       | `/api/v1/accounts`                                          |
  | Description    | Retrieves all accounts belonging to the authenticated user. |
  | Authentication | Required                                                    |

  ---

  ### 2. Request

  **Request Body:** None

  ```http
  GET /api/v1/accounts
  Authorization: Bearer {accessToken}
  ```

  | Field         | Type   | Required | Description                         |
  | ------------- | ------ | -------- | ----------------------------------- |
  | `accessToken` | String | Y        | JWT Access Token issued after login |

  ---

  ### 3. Response

  #### Success — `200 OK`

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

  | Field           | Type       | Description                 |
  | --------------- | ---------- | --------------------------- |
  | `accounts`      | Array      | List of the user's accounts |
  | `accountId`     | Long       | Account ID                  |
  | `accountNumber` | String     | Account number              |
  | `balance`       | BigDecimal | Account balance             |
  | `createdAt`     | DateTime   | Account creation time       |

  ---

  ### 4. Error Response

  #### Authentication Failed — `401 Unauthorized`

  ```json
  {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required."
  }
  ```

  ---

  ### 5. Status Codes

  | Status             | Description                     |
  | ------------------ | ------------------------------- |
  | `200 OK`           | Accounts retrieved successfully |
  | `401 Unauthorized` | User is not authenticated       |

* Get Account Details

  ### 1. API Information

  | Item           | Description                                                                  |
  | -------------- | ---------------------------------------------------------------------------- |
  | Method         | `GET`                                                                        |
  | Endpoint       | `/api/v1/accounts/{accountId}`                                               |
  | Description    | Retrieves details of a specific account belonging to the authenticated user. |
  | Authentication | Required                                                                     |

  ---

  ### 2. Request

  **Request Body:** None

  ```http
  GET /api/v1/accounts/1
  Authorization: Bearer {accessToken}
  ```

  | Parameter     | Type   | Required | Description                         |
  | ------------- | ------ | -------- | ----------------------------------- |
  | `accountId`   | Long   | Y        | Account ID to retrieve              |
  | `accessToken` | String | Y        | JWT Access Token issued after login |

  ---

  ### 3. Response

  #### Success — `200 OK`

  ```json
  {
    "accountId": 1,
    "accountNumber": "1234567890",
    "balance": 10000.00,
    "createdAt": "2026-09-23T10:00:00"
  }
  ```

  | Field           | Type          | Description           |
  | --------------- | ------------- | --------------------- |
  | `accountId`     | Long          | Account ID            |
  | `accountNumber` | String        | Account number        |
  | `balance`       | BigDecimal    | Account balance       |
  | `createdAt`     | LocalDateTime | Account creation time |

  ---

  ### 4. Error Response

  #### Authentication Failed — `401 Unauthorized`

  ```json
  {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required."
  }
  ```

  #### Account Not Found or Access Denied — `404 Not Found`

  ```json
  {
    "code": "ACCOUNT_NOT_FOUND",
    "message": "Account not found."
  }
  ```

  ---

  ### 5. Status Codes

  | Status             | Description                                           |
  | ------------------ | ----------------------------------------------------- |
  | `200 OK`           | Account details retrieved successfully                |
  | `401 Unauthorized` | User is not authenticated                             |
  | `404 Not Found`    | Account does not exist or does not belong to the user |

* Deposit

  ### 1. API Information

  | Item           | Description                                                         |
  | -------------- | ------------------------------------------------------------------- |
  | Method         | `POST`                                                              |
  | Endpoint       | `/api/v1/accounts/{accountId}/deposit`                              |
  | Description    | Deposits money into an account belonging to the authenticated user. |
  | Authentication | Required                                                            |

  ---

  ### 2. Request

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

  | Field       | Type       | Required | Description                       |
  | ----------- | ---------- | -------- | --------------------------------- |
  | `accountId` | Long       | Y        | ID of the account to deposit into |
  | `amount`    | BigDecimal | Y        | Deposit amount                    |

  ---

  ### 3. Response

  #### Success — `200 OK`

  ```json
  {
    "accountId": 1,
    "accountNumber": "1234567890",
    "amount": 10000.00,
    "balance": 20000.00
  }
  ```

  | Field           | Type       | Description                   |
  | --------------- | ---------- | ----------------------------- |
  | `accountId`     | Long       | Account ID                    |
  | `accountNumber` | String     | Account number                |
  | `amount`        | BigDecimal | Deposited amount              |
  | `balance`       | BigDecimal | Account balance after deposit |

  ---

  ### 4. Error Response

  #### Invalid Deposit Amount — `400 Bad Request`

  ```json
  {
    "code": "INVALID_AMOUNT",
    "message": "Deposit amount must be greater than zero."
  }
  ```

  #### Authentication Failed — `401 Unauthorized`

  ```json
  {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required."
  }
  ```

  #### Account Not Found or Access Denied — `404 Not Found`

  ```json
  {
    "code": "ACCOUNT_NOT_FOUND",
    "message": "Account not found."
  }
  ```

  ---

  ### 5. Status Codes

  | Status             | Description                                           |
  | ------------------ | ----------------------------------------------------- |
  | `200 OK`           | Deposit successful                                    |
  | `400 Bad Request`  | Deposit amount is invalid                             |
  | `401 Unauthorized` | User is not authenticated                             |
  | `404 Not Found`    | Account does not exist or does not belong to the user |

## 3. Transfer

* Transfer

  ### 1. API Information

  | Item           | Description                                                               |
  | -------------- | ------------------------------------------------------------------------- |
  | Method         | `POST`                                                                    |
  | Endpoint       | `/api/v1/transfers`                                                       |
  | Description    | Transfers money from the authenticated user's account to another account. |
  | Authentication | Required                                                                  |

  ---

  ### 2. Request

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

  | Field               | Type       | Required | Description                |
  | ------------------- | ---------- | -------- | -------------------------- |
  | `fromAccountNumber` | String     | Y        | Source account number      |
  | `toAccountNumber`   | String     | Y        | Destination account number |
  | `amount`            | BigDecimal | Y        | Transfer amount            |

  ---

  ### 3. Response

  #### Success — `200 OK`

  ```json
  {
    "transferId": 1,
    "fromAccountNumber": "1234567890",
    "toAccountNumber": "9876543210",
    "amount": 5000.00,
    "status": "COMPLETED"
  }
  ```

  | Field               | Type       | Description                |
  | ------------------- | ---------- | -------------------------- |
  | `transferId`        | Long       | Transfer ID                |
  | `fromAccountNumber` | String     | Source account number      |
  | `toAccountNumber`   | String     | Destination account number |
  | `amount`            | BigDecimal | Transfer amount            |
  | `status`            | String     | Transfer status            |

  ---

  ### 4. Error Response

  #### Invalid Transfer Amount — `400 Bad Request`

  ```json
  {
    "code": "INVALID_AMOUNT",
    "message": "Transfer amount must be greater than zero."
  }
  ```

  #### Authentication Failed — `401 Unauthorized`

  ```json
  {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required."
  }
  ```

  #### Account Not Found or Access Denied — `404 Not Found`

  ```json
  {
    "code": "ACCOUNT_NOT_FOUND",
    "message": "Account not found."
  }
  ```

  #### Insufficient Balance — `409 Conflict`

  ```json
  {
    "code": "INSUFFICIENT_BALANCE",
    "message": "Insufficient balance."
  }
  ```

  ---

  ### 5. Status Codes

  | Status             | Description                                                          |
  | ------------------ | -------------------------------------------------------------------- |
  | `200 OK`           | Transfer successful                                                  |
  | `400 Bad Request`  | Transfer amount is invalid                                           |
  | `401 Unauthorized` | User is not authenticated                                            |
  | `404 Not Found`    | Account does not exist or source account does not belong to the user |
  | `409 Conflict`     | Insufficient balance or transfer processing conflict                 |

## 4. Transaction

* Get Transaction History

  ### 1. API Information

  | Item           | Description                                                                               |
  | -------------- | ----------------------------------------------------------------------------------------- |
  | Method         | `GET`                                                                                     |
  | Endpoint       | `/api/v1/accounts/{accountId}/transactions`                                               |
  | Description    | Retrieves transaction history for a specific account belonging to the authenticated user. |
  | Authentication | Required                                                                                  |

  ---

  ### 2. Request

  ```http
  GET /api/v1/accounts/1/transactions
  Authorization: Bearer {accessToken}
  ```

  | Field       | Type    | Required | Description                     |
  | ----------- | ------- | -------- | ------------------------------- |
  | `accountId` | Long    | Y        | ID of the account               |
  | `page`      | Integer | N        | Page number                     |
  | `size`      | Integer | N        | Number of transactions per page |

  **Query Parameter Example:**

  ```http
  GET /api/v1/accounts/1/transactions?page=0&size=20
  Authorization: Bearer {accessToken}
  ```

  ---

  ### 3. Response

  #### Success — `200 OK`

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

  | Field           | Type          | Description                     |
  | --------------- | ------------- | ------------------------------- |
  | `transactionId` | Long          | Transaction ID                  |
  | `type`          | String        | Transaction type                |
  | `amount`        | BigDecimal    | Transaction amount              |
  | `status`        | String        | Transaction status              |
  | `createdAt`     | LocalDateTime | Transaction date and time       |
  | `page`          | Integer       | Current page number             |
  | `size`          | Integer       | Number of transactions per page |
  | `totalElements` | Long          | Total number of transactions    |
  | `totalPages`    | Integer       | Total number of pages           |

  ---

  ### 4. Error Response

  #### Authentication Failed — `401 Unauthorized`

  ```json
  {
    "code": "UNAUTHORIZED",
    "message": "Authentication is required."
  }
  ```

  #### Account Not Found or Access Denied — `404 Not Found`

  ```json
  {
    "code": "ACCOUNT_NOT_FOUND",
    "message": "Account not found."
  }
  ```

  ---

  ### 5. Status Codes

  | Status             | Description                                           |
  | ------------------ | ----------------------------------------------------- |
  | `200 OK`           | Transaction history retrieved successfully            |
  | `401 Unauthorized` | User is not authenticated                             |
  | `404 Not Found`    | Account does not exist or does not belong to the user |
