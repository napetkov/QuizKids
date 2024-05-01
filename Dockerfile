# Build the JAR file using Maven
FROM maven:3.8.7-eclipse-temurin-19

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean install

# Command to run the Spring Boot application when the container starts
CMD ["mvn", "spring-boot:run"]



