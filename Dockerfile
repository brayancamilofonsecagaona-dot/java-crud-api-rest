# Etapa 1: Compilación (Usamos Java 21 o superior si es necesario)
FROM maven:3-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]