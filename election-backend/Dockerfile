# Gebruik Java 11 JDK voor Render
FROM amazoncorretto:21

# Stel de werkdirectory in
WORKDIR /app

# Kopieer het jar-bestand
COPY target/election-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
# Start het Java-programma
ENTRYPOINT ["java", "-jar", "app.jar"]

