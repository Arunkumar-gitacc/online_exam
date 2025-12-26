# ================================
# Stage 1: Build the application
# ================================
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only pom.xml first (for caching)
COPY pom.xml .

# Download dependencies (cached layer)
RUN mvn dependency:go-offline -B

# Copy full source
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# ================================
# Stage 2: Run the application
# ================================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar



# Expose port used by Spring Boot
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]