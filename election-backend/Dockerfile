# Build stage
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/election-backend-0.0.1-SNAPSHOT.jar election-backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "election-backend.jar"]
