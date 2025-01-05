# Etap 1: Budowanie aplikacji
FROM maven:3.9.5-eclipse-temurin-17 AS builder

WORKDIR /app

# Instalacja Node.js, kompatybilnego npm i Tailwind CSS
RUN apt-get update && apt-get install -y curl && \
    curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g npm@10 --force && \
    npm install -g tailwindcss --force

# Kopiowanie plików projektu
COPY pom.xml ./
COPY src ./src

# Diagnostyka: sprawdzenie pliku input.css
RUN ls -l src/main/resources/static/

# Uruchamianie budowy projektu
RUN mvn clean package -DskipTests

# Etap 2: Obraz końcowy
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Kopiowanie zbudowanego pliku JAR
COPY --from=builder /app/target/frontend-web-1.0-SNAPSHOT.jar frontend.jar

# Eksponowanie portu aplikacji
EXPOSE 8080

# Uruchamianie aplikacji
ENTRYPOINT ["java", "-jar", "frontend.jar"]
