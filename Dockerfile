FROM openjdk:21-jdk-slim

WORKDIR /jobApplicationApp

COPY target/JobApplicationManagement-0.0.1-SNAPSHOT.jar jobApplicationApp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "jobApplicationApp.jar"]