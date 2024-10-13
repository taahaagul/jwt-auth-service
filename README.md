# springboot-jwt-security

This project demonstrates the implementation of security using Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features to help you secure your web application:

## Features

- User registration and login with JWT authentication
- Password encryption using BCrypt
- Role-based authorization with Spring Security
- Customized access denied handling
- Refresh token
- Email Validation
- Change Password
- Forget My Password

## Technologies

- [Spring Boot 3.0]
- [Spring Security]
- [JSON Web Tokens (JWT)]
- [BCrypt]
- [Maven]
- [mySQL]
- [OpenAPI]

## Getting Started 

To get started with this project, you will need to have the following installed on your local machine:

- JDK 17+
- Maven 3+

To build and run the project, follow these steps:

1. Clone the repository to your local machine using the following command:

```https://github.com/taahaagul/jwt-auth-service ```

2. Add database credentials in application.properties file

3. When users sign up, they must click on the link sent to their emails to activate their accounts. Otherwise, the login process cannot be completed.

4. The access token expires in 15 minutes, and the refresh token expires in 30 minutes.

5. For the 'Forgot Password' section, a verification token is sent to users' emails. Users can reset their passwords using this token within 4 minutes. Otherwise, the process will be rejected.

6. This platform supports Method Level Security. 

7. Everyone has the USER role by default when first registering on the platform

- The application will be available at 
```http://localhost:8080```
- All APIs of the platform are documented with Swagger. ``` http://localhost:8080/swagger-ui/index.html```
