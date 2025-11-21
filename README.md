
<img width="1536" height="1024" alt="ChatGPT Image Nov 21, 2025, 11_58_21 AM" src="https://github.com/user-attachments/assets/207586dd-aa14-4970-8996-0c2c309aca52" />


# YMStore

YMStore is a multi-module Java backend project for an online store. It includes separate services for products, inventory, orders, an API gateway, and service discovery.

## Modules

- **apigateway** – Routes all API requests to backend services
- **discoveryservice** – Service registry
- **product** – Product management
- **inventory** – Stock management
- **order** – Order management
- **base** – Shared/common components
- **prometheus** – Monitoring configuration

## Tech Stack

- Java
- Maven (multi-module)
- Spring Boot
- Spring Cloud Gateway
- Spring Cloud Discovery (Eureka)
- Prometheus (metrics)

## How to Run

### 1. Build the project
```
mvn clean install
```

### 2. Start the services (in this order)

**Discovery Service**
```
cd discoveryservice
mvn spring-boot:run
```

**Product Service**
```
cd product
mvn spring-boot:run
```

**Inventory Service**
```
cd inventory
mvn spring-boot:run
```

**Order Service**
```
cd order
mvn spring-boot:run
```

**API Gateway**
```
cd apigateway
mvn spring-boot:run
```

(Optional) **Prometheus**
```
cd prometheus
docker compose up
```
