# 1️⃣ Use official OpenJDK 17 Alpine image
FROM eclipse-temurin:17-jdk-alpine

# 2️⃣ Set working directory inside the container
WORKDIR /app

# 3️⃣ Copy the built Spring Boot jar into the container
# Make sure you build the jar first with Maven or Gradle
COPY target/authz-service-0.0.1-SNAPSHOT.jar app.jar

# 4️⃣ Expose the port that the service listens on
EXPOSE 8080

# 5️⃣ Default environment variables (can be overridden in Docker Compose)
ENV SPRING_PROFILES_ACTIVE=dev
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://authz-db:5432/authz_dev
ENV SPRING_DATASOURCE_USERNAME=dev_user
ENV SPRING_DATASOURCE_PASSWORD=dev_pass

# 6️⃣ Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
