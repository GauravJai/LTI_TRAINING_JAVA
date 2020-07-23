#FROM java:8
FROM openjdk:8-jdk-alpine
EXPOSE 5050
ADD /target/movie-app-0.0.1-SNAPSHOT.jar movie-app.jar
ENTRYPOINT ["java","-jar","movie-app.jar"]