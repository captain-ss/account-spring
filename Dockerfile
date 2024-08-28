# Use an official Maven image with JDK 11 as the base image
FROM maven:3.9.9-eclipse-temurin-17

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .

# Copy all project source files
COPY src ./src

# Run Maven to build the project and resolve dependencies
RUN mvn clean package

# Set the default command to run your application
CMD ["java", "-jar", "./target/account_balance-0.0.1-SNAPSHOT.jar"]
