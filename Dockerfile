FROM openjdk:21-jdk-slim

LABEL authors="eustache"

WORKDIR /app

EXPOSE 8080

# ADD the JAR file into the container
ADD target/job-application-management-system.jar job-application-management-system.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "job-application-management-system.jar"]
