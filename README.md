# 🚀 Telemetry Lab

A multi-service microservices project built with **Java Spring Boot**, **Apache Kafka**, and **MongoDB**, designed to simulate, process, and analyze **telemetry data streams**.

Perfect for:
- Learning **event-driven architecture**
- Practicing with **Kafka** producers/consumers
- Storing/querying telemetry in **MongoDB**
- Training **GitHub Copilot**

---

## 🧱 Architecture

[telemetry-producer] → (Kafka: telemetry.raw) → [telemetry-processor] → (Kafka: telemetry.processed) → [telemetry-store] → (MongoDB: telemetry_data) ↑ [telemetry-api] ← REST access to MongoDB


Each microservice is a Spring Boot app, with Kafka and Mongo orchestrated via Docker.

---

## 🧰 Tech Stack

- Java 17+
- Spring Boot 3+
- Apache Kafka
- MongoDB
- Docker + Docker Compose
- Spring Kafka, Spring Data MongoDB
- (Optional) Swagger/OpenAPI, Kafka UI, Mongo Express

---

## 📦 Modules

| Module              | Description                                      |
|---------------------|--------------------------------------------------|
| `telemetry-producer` | Simulates and streams random telemetry data to Kafka |
| `telemetry-processor` | Consumes raw telemetry, adds metadata, transforms |
| `telemetry-store`    | Persists processed telemetry into MongoDB        |
| `telemetry-api`      | REST API to query telemetry from MongoDB        |
| `telemetry-common`   | Shared DTOs and models (optional)               |

---

## 🚀 Getting Started

### 🔧 Requirements

- [Docker](https://www.docker.com/)
- Java 17+
- Maven

---

### 🐳 Start Kafka + MongoDB

```bash
docker-compose up -d
```
This will start:
- Kafka on localhost:9092
- MongoDB on localhost:27017
- Kafka UI on http://localhost:8080
- Mongo Express on http://localhost:8081

---

### ▶️ Run the Services
```bash
cd telemetry-producer && mvn spring-boot:run
cd telemetry-processor && mvn spring-boot:run
cd telemetry-store && mvn spring-boot:run
cd telemetry-api && mvn spring-boot:run
```

---

### 🧪 Running Tests

Each module includes unit and integration tests. To run all tests for a specific module, navigate to the module directory and execute:

```bash
mvn test
```

To run tests for all modules, execute the following command from the root directory:

```bash
mvn test
```

Ensure that Kafka and MongoDB are running via Docker before running integration tests.

---

### 📡 API Endpoints
Available from telemetry-api on http://localhost:8082
- GET /api/telemetry/latest/{deviceId}
- GET /api/telemetry/range?start=2024-04-01T00:00:00Z&end=2024-04-10T00:00:00Z
- GET /api/telemetry/low-battery?threshold=20

If Swagger is enabled:
- Swagger UI: http://localhost:8082/swagger-ui.html
`