# Use an OpenJDK runtime as base image
FROM openjdk:17-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container at the defined working directory
COPY target/*.jar app.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "/app.jar"]
