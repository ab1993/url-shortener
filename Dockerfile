FROM eclipse-temurin:21-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy jar from target folder to container
COPY target/url-shortner-0.0.1-SNAPSHOT.jar app.jar

# Expose port (important for Spring Boot)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]