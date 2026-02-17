# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
./mvnw clean install          # Full build
./mvnw spring-boot:run        # Run application (requires MySQL on 127.0.0.1:3306, database: todoapp)
./mvnw test                   # Run all tests
./mvnw test -Dtest=TodoApplicationTests  # Run a single test class
```

**Prerequisites:** Java 17, MySQL 8.0+ running locally with database `todoapp`.

## Architecture

Spring Boot 3.2.1 REST API for todo management with JWT authentication. Follows standard layered architecture:

**Controller → Service → Repository → Model**

- **Models** (`model/`): `Basemodel` (abstract, provides auto-generated `id`), `User` (has `OneToMany` relationship to `Todo`), `Todo` (description, targetDate, isDone)
- **Repositories** (`repository/`): Spring Data JPA interfaces extending `JpaRepository`
- **Services** (`service/`): Business logic with constructor-injected repositories. `TodoService` manually iterates user's todo list for lookups rather than using JPA queries
- **Controllers** (`controller/`): REST endpoints — `UserController` at `/users`, `TodoController` at `/users/{userId}/todos`

## Security

JWT-based stateless authentication configured in `jwt/JwtSecurityConfig.java`:
- RSA 2048-bit key pair generated at startup for token signing
- `POST /authenticate` is public; all other endpoints require a valid JWT
- In-memory user: `shlokagrawal` / `1` with role `USER`
- Token expiry: 90 minutes
- `security/BasicAuthenticationSecurityConfiguration.java` is legacy (disabled)

## API Endpoints

| Method | Path | Auth |
|--------|------|------|
| POST | `/authenticate` | Public |
| GET/POST | `/users`, `/users/{id}` | JWT |
| DELETE | `/users/{id}` | JWT |
| GET/POST/PUT/DELETE | `/users/{userId}/todos[/{todoId}]` | JWT |

## Key Configuration

- **CORS**: Allows `http://localhost:3000` (frontend dev server) on all endpoints
- **Hibernate**: `ddl-auto=update` — schema auto-managed, SQL logging enabled
- **Lombok**: Used on all model classes for getters/setters
