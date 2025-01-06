# Faza budowy
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY ./services/support-service/pom.xml ./services/support-service/pom.xml
COPY ./services/support-service/src ./services/support-service/src
COPY ./config ./config

WORKDIR /app/services/support-service

RUN mvn clean package -DskipTests

# Faza końcowa
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/services/support-service/target/support-service-1.0.0.jar support-service.jar
COPY --from=builder /app/services/support-service/src/main/resources/application.properties /app/config/application.properties
COPY ./config/database/db.changelog-master.yaml /app/db/changelog/db.changelog-master.yaml

EXPOSE 8085

# Uruchamianie aplikacji
CMD ["java", "-jar", "support-service.jar", "--spring.config.location=/app/config/application.properties"]
