# Etapa de compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copiamos solo el pom.xml primero para aprovechar la caché de Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]