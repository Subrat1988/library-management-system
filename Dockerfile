# Use a base image with Java 21
FROM openjdk:17

COPY build/libs/library-management-system-0.0.1-SNAPSHOT.jar library-management-system.jar

ENTRYPOINT ["java", "-jar", "library-management-system.jar"]