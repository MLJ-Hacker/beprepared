FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17 -oracle
COPY . .
RUN apt-get install maven -oracle
RUN mvn clean install

FROM openjdk:17-slim
COPY --from=build /target/*.jar ./beprepared.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "beprepared.jar"]
