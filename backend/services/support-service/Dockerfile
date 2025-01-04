# Faza budowy
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

# Faza końcowa
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/support-service-1.0.0.jar support-service.jar
COPY src/main/resources/application.properties /app/config/application.properties

EXPOSE 8080

# Uruchamianie aplikacji
CMD ["java", "-jar", "support-service.jar", "--spring.config.location=/app/config/application.properties"]
