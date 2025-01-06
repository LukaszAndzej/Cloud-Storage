# Faza budowy
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY ./services/payment-service/pom.xml ./services/payment-service/pom.xml
COPY ./services/payment-service/src ./services/payment-service/src
COPY ./config ./config

WORKDIR /app/services/payment-service

RUN mvn clean package -DskipTests

# Faza końcowa
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/services/payment-service/target/payment-service-1.0.0.jar payment-service.jar
COPY --from=builder /app/services/payment-service/src/main/resources/application.properties /app/config/application.properties
COPY ./config/database/db.changelog-master.yaml /app/db/changelog/db.changelog-master.yaml

EXPOSE 8084

# Uruchamianie aplikacji
CMD ["java", "-jar", "payment-service.jar", "--spring.config.location=/app/config/application.properties"]
