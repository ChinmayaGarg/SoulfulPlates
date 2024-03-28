# Soulful Plates

This project demonstrates building a secure web application with Spring Boot, utilizing JSON Web Tokens (JWT) for authentication. It showcases Spring Boot's streamlined approach to integrating security, data persistence, web services, and validation.

## Project Dependencies

Here's a detailed look at the project's dependencies, including their purpose and links to their Maven Central Repository pages.

### Spring Boot Starters

Starters are a set of convenient dependency descriptors that you can include in your application.

- **Spring Boot Starter Data JPA**

  - **Purpose**: Simplifies data persistence with JPA.
  - [Maven Central](https://search.maven.org/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
  - [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

- **Spring Boot Starter Security**

  - **Purpose**: Adds authentication and authorization capabilities.
  - [Maven Central](https://search.maven.org/artifact/org.springframework.boot/spring-boot-starter-security)
  - [Spring Security Reference](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)

- **Spring Boot Starter Validation**

  - **Purpose**: Supports method validation with Hibernate Validator.
  - [Maven Central](https://search.maven.org/artifact/org.springframework.boot/spring-boot-starter-validation)
  - [Spring Validation Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation)

- **Spring Boot Starter Web**
  - **Purpose**: Facilitates building web and RESTful applications.
  - [Maven Central](https://search.maven.org/artifact/org.springframework.boot/spring-boot-starter-web)
  - [Spring Web Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-web-applications)

### Database Connector

- **MySQL Connector/J**
  - **Purpose**: Connects your application to MySQL databases.
  - [Maven Central](https://search.maven.org/artifact/mysql/mysql-connector-java)
  - [MySQL Connector/J Documentation](https://dev.mysql.com/doc/connector-j/8.0/en/)

### JSON Web Token Support

- **JJWT (Java JWT)**
  - **Purpose**: A Java library for creating and parsing JSON Web Tokens.
  - [Maven Central for jjwt-api](https://search.maven.org/artifact/io.jsonwebtoken/jjwt-api)
  - [Maven Central for jjwt-impl](https://search.maven.org/artifact/io.jsonwebtoken/jjwt-impl)
  - [Maven Central for jjwt-jackson](https://search.maven.org/artifact/io.jsonwebtoken/jjwt-jackson)
  - [JJWT GitHub Repository](https://github.com/jwtk/jjwt)

### Testing

- **Spring Boot Starter Test**

  - **Purpose**: Facilitates testing of Spring Boot applications.
  - [Maven Central](https://search.maven.org/artifact/org.springframework.boot/spring-boot-starter-test)

- **Spring Security Test**

  - **Purpose**: Provides support for testing Spring Security.
  - [Maven Central](https://search.maven.org/artifact/org.springframework.security/spring-security-test)

- **JUnit**
  - **Purpose**: A framework for writing and running tests in Java.
  - [Maven Central](https://search.maven.org/artifact/junit/junit)
  - [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

### Code Enhancement

- **Project Lombok**
  - **Purpose**: Simplifies Java code and reduces boilerplate.
  - [Maven Central](https://search.maven.org/artifact/org.projectlombok/lombok)
  - [Project Lombok](https://projectlombok.org/)

## Running the Project

To run the project, ensure Java 17 and Maven are installed. Follow these steps:

1. Clone the repository.
2. Navigate to the project directory.
3. Execute `mvn spring-boot:run`.

The application will be accessible at `localhost:8080`.

## License

This project is licensed under the MIT License.
