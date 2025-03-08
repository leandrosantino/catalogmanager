FROM openjdk:17-jdk-slim
WORKDIR /usr/src/app

COPY ./build/libs/catalogmanager-0.1.jar ./api.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "./api.jar", "--spring.profiles.active=prod"]