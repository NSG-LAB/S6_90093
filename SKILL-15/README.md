# Corporate Portal JWT Authentication

This Spring Boot application secures a corporate portal with JWT-based authentication and role-based authorization. `ADMIN` users can manage employee records, while `EMPLOYEE` users can access their profile information.

## Prerequisites

- Java 17+
- Maven 3.9+

## Running the application

```bash
mvn spring-boot:run
```

The API runs at `http://localhost:8080`. H2 console is available at `/h2-console` (JDBC URL `jdbc:h2:mem:portaldb`).

## Default users

| Username  | Password  | Role    |
|-----------|-----------|---------|
| admin     | admin123  | ADMIN   |
| employee1 | emp123    | EMPLOYEE|

## Authentication flow

1. `POST /login` with JSON body `{ "username": "admin", "password": "admin123" }`.
2. Copy the `token` value from the response.
3. Add header `Authorization: Bearer <token>` to subsequent requests.

## Secured endpoints

- `POST /admin/add` – create new employee (ADMIN only)
- `DELETE /admin/delete/{username}` – delete employee (ADMIN only)
- `GET /employee/profile` – fetch authenticated employee profile (EMPLOYEE or ADMIN)

### Sample request bodies

```json
// POST /admin/add
{
  "username": "employee2",
  "password": "emp456",
  "fullName": "Employee Two",
  "department": "HR"
}
```

## Testing with Postman

1. Obtain a JWT via `/login`.
2. Add the `Authorization` header using the token and test each endpoint.
3. Call `/admin/add` and `/admin/delete/{username}` with and without the header to verify role restrictions.
4. Remove or tamper with the token (e.g., change a character) to reproduce `401 Unauthorized` responses.
5. Use an `EMPLOYEE` token against `/admin/*` routes to confirm `403 Forbidden` responses.

## Project structure

- `config` – security config and sample data seeding
- `controller` – REST endpoints
- `dto` – request/response records
- `model` – JPA entities
- `repository` – Spring Data repositories
- `security` – JWT filter
- `service` – business logic and helpers
- `exception` – centralized error responses
