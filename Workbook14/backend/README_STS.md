# Backend Setup in STS (Spring Tool Suite)

This backend provides APIs for:

- Register user
- Login user
- Fetch profile by stored `userId` or `username`

## 1. Prerequisites

- Java 17+
- STS 4 (latest)
- Maven (or use STS embedded Maven)

## 2. Import project in STS

1. Open STS.
2. Click **File > Import > Maven > Existing Maven Projects**.
3. Browse to this folder: `backend/`
4. Select `pom.xml` and finish import.
5. Wait for Maven dependencies to download.

## 3. Run the Spring Boot app

1. In Package Explorer, open `src/main/java/com/example/authapp/AuthappApplication.java`.
2. Right-click file > **Run As > Spring Boot App**.
3. Verify app started on:
   - `http://localhost:8080`

## 4. Database details

Current configuration uses H2 in-memory database.

- H2 console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:authdb`
- User: `sa`
- Password: (empty)

## 5. API endpoints

Base URL: `http://localhost:8080/api`

### Register
- Method: `POST`
- URL: `/auth/register`
- Body:
```json
{
  "username": "john",
  "fullName": "John Doe",
  "email": "john@example.com",
  "password": "123456"
}
```

### Login
- Method: `POST`
- URL: `/auth/login`
- Body:
```json
{
  "username": "john",
  "password": "123456"
}
```
- Response:
```json
{
  "userId": 1,
  "username": "john"
}
```

### Profile by userId
- Method: `GET`
- URL: `/users/profile?userId=1`

### Profile by username
- Method: `GET`
- URL: `/users/profile?username=john`

## 6. CORS

CORS is enabled for frontend dev server:

- `http://localhost:5173`

If your frontend port changes, update CORS in:

- `src/main/java/com/example/authapp/config/CorsConfig.java`

## 7. Switch to MySQL (optional)

If you want persistent DB instead of H2:

1. Add MySQL driver dependency in `pom.xml`.
2. Replace DB properties in `src/main/resources/application.properties`.
3. Create database (example: `authdb`).
4. Restart app.

## 8. Common STS troubleshooting

- Maven errors:
  - Right click project > **Maven > Update Project**.
- Java version mismatch:
  - Right click project > **Properties > Java Compiler** and set to 17.
- Port already in use:
  - Change `server.port` in `application.properties`.
