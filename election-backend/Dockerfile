# Gebruik een JDK-afbeelding als basis
FROM openjdk:17-jdk-slim

# Stel de werkdirectory in
WORKDIR /app

# Kopieer de jar file naar de container
COPY target/election-backend-0.0.1-SNAPSHOT.jar app.jar

# Exposeer poort 8080
EXPOSE 8080

# Voer de jar uit
ENTRYPOINT ["java", "-jar", "app.jar"]
