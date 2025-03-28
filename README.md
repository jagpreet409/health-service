Healthcare Microservices System
===============================

Overview
--------

A distributed healthcare management system built with Spring Boot microservices, featuring:

*   Patient management
    
*   Billing processing
    
*   Notification services
    
*   Authentication and authorization
    

Service Components
------------------

### 1\. Patient Service

**Responsibilities**:

*   Patient record management
    
*   Appointment scheduling
    
*   Integration with billing service
    

**Environment Variables**:


`BILLING_SERVICE_ADDRESS=billing-service  BILLING_SERVICE_GRPC_PORT=9005  SPRING_DATASOURCE_URL=jdbc:postgresql://patient-service-db:5432/db  SPRING_DATASOURCE_USERNAME=[DB_USERNAME]  SPRING_DATASOURCE_PASSWORD=[DB_PASSWORD]  SPRING_JPA_HIBERNATE_DDL_AUTO=update  SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092   `

### 2\. Billing Service

**Features**:

*   gRPC API for payment processing
    
*   Transaction management
    

**Required Dependencies**:

`io.grpc grpc-netty-shaded      1.69.0`


### 3\. Notification Service

**Features**:

*   Kafka message consumption
    
*   Patient notification delivery
    

**Configuration**:

` spring.kafka.bootstrap-servers=kafka:9092   `

### 4\. Auth Service

**Features**:

*   JWT-based authentication
    
*   Role-based access control
    
*   User management
    

**Database Setup**:

`  CREATE TABLE IF NOT EXISTS users (      id UUID PRIMARY KEY,      email VARCHAR(255) UNIQUE NOT NULL,      password VARCHAR(255) NOT NULL,      role VARCHAR(50) NOT NULL  );   `

Infrastructure Requirements
---------------------------

### Kafka Configuration


`KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092,EXTERNAL://localhost:9094  KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093,EXTERNAL://:9094   `

### Database Setup


`POSTGRES_DB=db  POSTGRES_USER=[DB_USERNAME]  POSTGRES_PASSWORD=[DB_PASSWORD]   `

Development Setup
-----------------

1.  **Prerequisites**:
    
    *   Java 21+
        
    *   Docker and Docker Compose
        
    *   Maven
        
2.  mvn clean installdocker-compose builddocker-compose up -d
    
3.  **Access services**:
    
    *   Patient Service: http://localhost:8081
        
    *   Auth Service: http://localhost:8082
        
    *   (Adjust ports as needed)
        
    

Security
--------

All services implement:

*   HTTPS encryption
    
*   JWT authentication
    
*   Role-based authorization
    
*   Secure credential storage
    

Testing
-------

Run test suites with:


`  mvn test   `

Integration tests require:

*   Running Docker containers
    
*   Kafka broker availability
    
*   Database connectivity
    

Deployment
----------

Production deployment recommendations:

1.  Use environment-specific configuration profiles
    
2.  Implement secrets management
    
3.  Configure health checks and monitoring
    
4.  Set up proper logging and alerting
    

Information
-------

This version:

*   Removes all hardcoded credentials
    
*   Eliminates duplicate content
    
*   Organizes information logically
    
*   Maintains all technical details
    
*   Uses generic placeholders for sensitive values
    
*   Provides clear structure for easy navigation

*  Add dependencies for GRPC and Kafka separately in your setup
