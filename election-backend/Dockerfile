FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
 
# Use a specific version of the OpenJDK image
FROM openjdk:17.0.1-jdk-slim
 
# Copy the JAR file into the container
COPY --from=build /target/election-backend-0.0.1-SNAPSHOT.jar election-backend.jar
 
# Expose the port your application will run on
EXPOSE 8080
 
# Command to run the JAR file .
ENTRYPOINT ["java", "-jar", "election-backend.jar"]