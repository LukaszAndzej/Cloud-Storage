# Faza budowy
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

# Faza końcowa
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/payment-service-1.0.0.jar payment-service.jar
COPY src/main/resources/application.properties /app/config/application.properties

EXPOSE 8080

# Uruchamianie aplikacji
CMD ["java", "-jar", "payment-service.jar", "--spring.config.location=/app/config/application.properties"]
