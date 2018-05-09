FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY auth-1.4.4.RELEASE.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]