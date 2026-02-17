# Todo Application

A REST API for todo management built with Spring Boot 3.2.1 and JWT authentication.

## Tech Stack

- **Java 17** + **Spring Boot 3.2.1**
- **Spring Security** with JWT (RSA 2048-bit, OAuth2 Resource Server)
- **Spring Data JPA** + **MySQL 8.0+**
- **Lombok** for boilerplate reduction
- **Maven** (wrapper included)

## Prerequisites

- Java 17
- MySQL 8.0+ running on `127.0.0.1:3306`
- Create a database named `todoapp`:
  ```sql
  CREATE DATABASE todoapp;
  ```

## Setup

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd todo-application
   ```

2. Update MySQL credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=your_password_here
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

   The server starts at `http://localhost:8080`.

## Build & Test

```bash
./mvnw clean install          # Full build
./mvnw test                   # Run all tests
./mvnw test -Dtest=TodoApplicationTests  # Run a single test class
```

## API Endpoints

### Authentication

| Method | Path | Auth | Description |
|--------|------|------|-------------|
| POST | `/authenticate` | Public | Get a JWT token |

**Request body:**
```json
{
  "username": "shlokagrawal",
  "password": "1"
}
```

**Response:**
```json
{
  "token": "<jwt-token>"
}
```

### Users

| Method | Path | Description |
|--------|------|-------------|
| GET | `/users` | List all users |
| GET | `/users/{id}` | Get user by ID |
| POST | `/users` | Create a user |
| DELETE | `/users/{id}` | Delete a user |

### Todos

| Method | Path | Description |
|--------|------|-------------|
| GET | `/users/{userId}/todos` | List todos for a user |
| GET | `/users/{userId}/todos/{todoId}` | Get a specific todo |
| POST | `/users/{userId}/todos` | Create a todo |
| PUT | `/users/{userId}/todos/{todoId}` | Update a todo |
| DELETE | `/users/{userId}/todos/{todoId}` | Delete a todo |

All user and todo endpoints require a valid JWT in the `Authorization` header:
```
Authorization: Bearer <jwt-token>
```

## Project Structure

```
src/main/java/com/shlok/todoapplication/
├── TodoApplication.java              # Entry point
├── controller/
│   ├── TodoController.java           # /users/{userId}/todos endpoints
│   └── UserController.java           # /users endpoints
├── model/
│   ├── Basemodel.java                # Abstract base with auto-generated ID
│   ├── Todo.java                     # description, targetDate, isDone
│   └── User.java                     # OneToMany relationship to Todo
├── repository/
│   ├── TodoRepository.java           # JPA repository for Todo
│   └── UserRepository.java           # JPA repository for User
├── service/
│   ├── TodoService.java              # Todo business logic
│   └── UserService.java              # User business logic
├── jwt/
│   ├── JwtAuthenticationController.java  # POST /authenticate
│   ├── JwtSecurityConfig.java            # Security filter chain config
│   ├── JwtTokenService.java              # Token creation & validation
│   ├── JwtTokenRequest.java              # Login request record
│   └── JwtTokenResponse.java             # Login response record
└── security/
    └── BasicAuthenticationSecurityConfiguration.java  # Legacy (disabled)
```

## Configuration

- **CORS**: Allows `http://localhost:3000` for frontend development
- **Hibernate**: `ddl-auto=update` — tables are auto-created/updated on startup
- **JWT**: RSA key pair generated at startup, tokens expire after 90 minutes
