# Use a newer version of Java (e.g., openjdk:17)
FROM openjdk:17-jdk

WORKDIR /app

# Copy the generated JAR file from the target directory
COPY target/election-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for communication
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
