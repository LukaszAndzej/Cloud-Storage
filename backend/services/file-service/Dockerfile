# Faza budowy
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY ./services/file-service/pom.xml ./services/file-service/pom.xml
COPY ./services/file-service/src ./services/file-service/src
COPY ./config ./config

WORKDIR /app/services/file-service

RUN mvn clean package -DskipTests

# Faza końcowa
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/services/file-service/target/file-service-1.0.0.jar file-service.jar
COPY --from=builder /app/services/file-service/src/main/resources/application.properties /app/config/application.properties
COPY ./config/database/db.changelog-master.yaml /app/db/changelog/db.changelog-master.yaml

EXPOSE 8080

# Uruchamianie aplikacji
CMD ["java", "-jar", "file-service.jar", "--spring.config.location=/app/config/application.properties"]
