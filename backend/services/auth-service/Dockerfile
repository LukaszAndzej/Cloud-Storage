# Faza budowy
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY ./services/auth-service/pom.xml ./services/auth-service/pom.xml
COPY ./services/auth-service/src ./services/auth-service/src
COPY ./config ./config

WORKDIR /app/services/auth-service

RUN mvn clean package -DskipTests

# Faza końcowa
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/services/auth-service/target/auth-service-1.0.0.jar auth-service.jar
COPY --from=builder /app/services/auth-service/src/main/resources/application.properties /app/config/application.properties
COPY ./config/database/db.changelog-master.yaml /app/db/changelog/db.changelog-master.yaml

EXPOSE 8082

# Uruchamianie aplikacji
CMD ["java", "-jar", "auth-service.jar", "--spring.config.location=/app/config/application.properties"]
