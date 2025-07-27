# SecureBank API

**SecureBank** is a RESTful API developed in Java using **Spring Boot** and **Spring Security**. It provides a secure backend structure for a banking system using **JWT-based authentication** and **role-based access control** (e.g., Admin, User).

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- H2 / PostgreSQL (configurable)
- Maven
- Lombok

## ✨ Features

- 🔐 Secure JWT authentication and token generation.
- 🔒 Role-based authorization (`ADMIN`, `USER`, etc.).
- 🧾 Endpoint protection according to user roles.
- 🔄 User registration and login system.
- 📃 Optional API documentation with Swagger.

## 📁 Project Structure

```

securebank/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/securebank/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── security/
│   │   │       └── service/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (optional)
└── pom.xml

```

## 🔑 Security

The application uses JWT (JSON Web Tokens) for secure stateless authentication:

- Users receive a token after successful authentication.
- The token must be included in the `Authorization` header for all protected requests.
- Access to endpoints is restricted based on user roles (e.g., only `ADMIN` can access certain resources).

### Example Authorization Header

```

Authorization: Bearer <token>

````

## 🧪 Testing

You can test the API using tools like Postman, Thunder Client, or Swagger UI (if enabled):

1. Register a new user or admin.
2. Authenticate to obtain a JWT.
3. Use the token to access protected routes based on your role.
