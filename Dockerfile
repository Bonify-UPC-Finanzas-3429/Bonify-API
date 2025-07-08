FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/Bonify-API-REST-0.0.1-SNAPSHOT.jar app.jar

# Puerto expuesto
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]
