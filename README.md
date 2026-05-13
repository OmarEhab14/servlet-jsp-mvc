Servlet-JSP-MVC Ecommerce
# Servlet-JSP-MVC Ecommerce

A university educational ecommerce web application built using Java Servlets, JSP, and the MVC architectural pattern.

The project demonstrates backend web development concepts including authentication, authorization, session management, Redis caching, rate limiting, product management, and review handling using a clean layered architecture without relying on frameworks such as Spring Boot.

---

## Features

### Authentication & Authorization
- User registration and login
- Backend-side validation
- Session-based authentication using Redis
- JWT-based authentication support
- Role-based access control (`USER` / `ADMIN`)
- Logout with token invalidation support

### Product Management
- Admin dashboard for managing products
- Add new products
- Delete products
- View product details
- Product listing page

### Reviews System
- Users can add reviews on products
- Product rating support
- Reviews displayed on product details page

### Performance & Security
- Redis session storage
- Redis product caching
- Redis-based rate limiting
- HikariCP connection pooling
- BCrypt password hashing
- Global exception handling
- Authentication & authorization filters

---

## Tech Stack

### Backend
- Java 17
- Jakarta Servlet API 6
- JSP (Jakarta Server Pages)
- Apache Tomcat 11

### Database & Caching
- MySQL
- Redis

### Libraries
- HikariCP
- Jedis
- BCrypt
- JJWT
- Jackson
- Lombok
- SLF4J

### DevOps
- Docker
- Docker Compose
- Maven

---

## Architecture

The project follows a layered MVC architecture:

```text
Client
   ↓
Filters
   ↓
Controllers (Servlets)
   ↓
Services
   ↓
DAOs
   ↓
Database / Redis
```
Project Structure
```text
src/main/java/com/advprog/servletecommerce
│
├── application
│   ├── services
│   ├── validators
│   ├── mappers
│   ├── security
│   └── enums
│
├── configs
│   ├── AppInitializer
│   ├── DatabaseConfig
│   └── RedisConfig
│
├── domain
│   ├── entities
│   ├── dao
│   ├── dto
│   └── exceptions
│
└── infrastructure
    ├── controllers
    ├── filters
    └── dao implementations
```
Authentication Flow
Session Authentication
User logs in successfully
A session identifier is generated
Session data is stored in Redis
Session cookie is sent to the client
Authentication filter validates session existence
JWT Authentication
JWT token is generated after successful login
Protected routes validate the token
Logout invalidates the token using Redis blacklist storage
Redis Usage

Redis is used for:

Session management
Product caching
Token blacklist storage
Rate limiting
Product Caching

Products and product details are cached in Redis to reduce database load and improve response times for frequently accessed pages.

Future Improvements
MongoDB integration for reviews
Shopping cart functionality
Order management
Product image uploads
Pagination & search
Admin analytics dashboard
Dockerized application deployment
CI/CD pipeline integration
Unit & integration testing
Database Technologies
Current
MySQL for relational data
Redis for caching and sessions
Planned
MongoDB for scalable review storage
Running the Project
Prerequisites

Make sure you have installed:

Java 17
Maven
Docker
Docker Compose
Apache Tomcat 11
Clone the Repository
git clone <your-repository-url>
cd Servlet-JSP-MVC-ecommerce
Start MySQL & Redis
```bash
docker compose up -d
```
Docker Compose
```docker
services:
  adv_db:
    image: mysql:oraclelinux9
    container_name: adv_db_mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_PASSWORD: root
      MYSQL_DATABASE: adv_db
    volumes:
      - adv_db_data:/var/lib/mysql
    networks:
      - adv_network
    ports:
      - "3306:3306"
    restart: "unless-stopped"

  redis:
    image: redis:7-alpine
    container_name: adv_redis
    networks:
      adv_network:
    volumes:
      - redis_data:/data
    ports:
      - "6379:6379"

networks:
  adv_network:
    driver: bridge

volumes:
  adv_db_data:
  redis_data:
```
Build the Project
```bash
mvn clean package
```

The generated WAR file will be located inside:

target/
Deploy to Tomcat

Copy the generated WAR file into:

tomcat/webapps/

Then start Tomcat.

Application Screens
Authentication
Login Page
Register Page
User
Home Page
Product Details Page
Add Review
Admin
Admin Dashboard
Product Management
Security Features
BCrypt password hashing
Route protection using filters
Session expiration handling
JWT validation
Redis token blacklist
Rate limiting
Educational Goals

This project was built to practice:

Servlet & JSP development
MVC architecture
Authentication & authorization
Redis integration
JDBC & connection pooling
Clean project structure
Backend validation
Exception handling
Role-based access control
License

This project is intended for educational purposes.
