# Use an official OpenJDK 17 image
FROM eclipse-temurin:17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR from target
COPY target/management-0.0.1.jar app.jar

# Expose the port your Spring Boot app uses
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java","-jar","/app/app.jar"]
