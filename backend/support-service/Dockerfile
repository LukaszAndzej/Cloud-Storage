FROM maven:3.9.5-eclipse-temurin-17 as builder

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Final image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Uruchamianie aplikacji
CMD ["java", "-jar", "support-service.jar"]
