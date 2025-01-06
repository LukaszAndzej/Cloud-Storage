# Faza budowy
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

COPY ./services/admin-panel/pom.xml ./services/admin-panel/pom.xml
COPY ./services/admin-panel/src ./services/admin-panel/src
COPY ./config ./config

WORKDIR /app/services/admin-panel

# Budowanie aplikacji
RUN mvn clean package -DskipTests

# Faza końcowa
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Kopiowanie zbudowanego JAR-a z fazy budowy
COPY --from=builder /app/services/admin-panel/target/admin-panel-1.0.0.jar admin-panel.jar
COPY --from=builder /app/services/admin-panel/src/main/resources/application.properties /app/config/application.properties

# Kopiowanie pliku konfiguracyjnego
COPY ./config/database/db.changelog-master.yaml /app/db/changelog/db.changelog-master.yaml

# Eksponowanie portu
EXPOSE 8080

# Uruchamianie aplikacji
CMD ["java", "-jar", "admin-panel.jar", "--spring.config.location=/app/config/application.properties"]
