FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/mini-file-system.jar mini-file-system.jar
ENTRYPOINT ["java", "-jar", "/mini-file-system.jar"]
