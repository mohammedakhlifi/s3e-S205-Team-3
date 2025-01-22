# Gebruik een Java 17 image als basis
FROM openjdk:17-jdk-slim

# Zet de werkdirectory naar /app
WORKDIR /app

# Kopieer de jar file naar de container (vervang de naam met de juiste jar-bestandnaam)
COPY target/your-spring-boot-app.jar app.jar

# Exposeer poort 8080
EXPOSE 8080

# Start de Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
