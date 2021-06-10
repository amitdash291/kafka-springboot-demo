FROM openjdk:17-ea-14-jdk-alpine3.13
ARG SERVICE_JAR=build/libs/*.jar
COPY ${SERVICE_JAR} service.jar
ENTRYPOINT ["java", "-jar", "/service.jar"]