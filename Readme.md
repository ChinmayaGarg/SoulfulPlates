# Soulful Plates

This project demonstrates building a secure web application with Spring Boot, utilizing JSON Web Tokens (JWT) for authentication. It showcases Spring Boot's streamlined approach to integrating security, data persistence, web services, and validation.

This project demonstrates how to build a secure web application with Spring Boot using JSON Web Tokens (JWT) for authentication. It showcases the integration of security, data persistence (JPA), validation, and web services in a Spring Boot application, focusing on securing RESTful APIs with JWT.

## Prerequisites

Ensure you have the following installed on your system before proceeding:

- **Java JDK 17**: Necessary for compiling and running the Java application.
- **Maven**: For project building and dependency management.
- **MySQL**: As the relational database management system for the project.

Verify your Java and Maven installations by running `java -version` and `mvn -version` in your terminal.

## Setting Up the Development Environment

### Database Setup

1. Install MySQL and create a new database named `soulfulplates`:
   ```sql
   CREATE DATABASE soulfulplates;
   ```

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

## Running the Project Locally

To run the project on your local machine, follow these steps:

1. **Clone the Repository**

   First, clone the project repository to your local machine using the following command in your terminal:

   ```bash
   git clone <repository-url>

   Make sure to replace `<repository-url>` with the actual URL of the project's repository.

   ```

2. **Navigate to the Project Directory**

Change into the project directory by running:

```bash
   cd spring-boot-security-jwt

   This command assumes that `spring-boot-security-jwt` is the name of the directory created when you cloned the repository. Adjust the command according to the actual directory name if it's different.

```

3. **Build the Project**

Build the project and run the tests by executing the following Maven command:

```bash
mvn clean install

This command compiles the project and runs any configured tests. It's important to ensure that the build and tests pass before trying to run the application.

```

4. **Run the Application**

Finally, start the Spring Boot application with:

```bash

mvn spring-boot:run

 This command starts the application on your local machine. By default, the application will be accessible at `http://localhost:8080`.

```

You can now interact with the application through your web browser or API testing tools like Postman.

## License

This project is licensed under the MIT License.

```

```
