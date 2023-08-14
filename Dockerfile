FROM openjdk:17-oracle
MAINTAINER arman
COPY ../docker/back.jar spring-backend.jar
ENTRYPOINT ["java", "-jar", "spring-backend.jar"]