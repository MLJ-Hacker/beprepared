FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install jdk-17 -y
COPY . .
RUN apt-get install maven -y
RUN mvn clean install

FROM jdk:17-slim
COPY --from=build /target/*.jar ./beprepared.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "beprepared.jar"]