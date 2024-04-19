# Stage 1: Build the JAR file using Maven
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package

# Stage 2: Create the final Docker image with the JAR file
FROM apache/beam_java17_sdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "your-spring-boot-app.jar"]
